package com.deloitte.service.utility.procedure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deloitte.returns.entity.filecounter.ReturnCountCrnJson;
import com.deloitte.returns.repository.ReturnCountCrnJsonRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class CrnProcedureService {

	private static final Logger log = LoggerFactory.getLogger(CrnProcedureService.class);

	private static final String PROCEDURE_NAME = "filecounter.insert_crn";

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private ReturnCountCrnJsonRepository returnCountCrnJsonRepository;

	public CrnProcedureService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Executes PostgreSQL procedure: filecounter.insert_crn
	 *
	 * @param startDt     API start timestamp
	 * @param endDt       API end timestamp
	 * @param crnCnt      Total CRN count
	 * @param isSuccess   API success flag
	 * @param msgJson     Message JSON
	 * @param crnDataJson Full CRN JSON payload
	 */
	public void executeInsertCrnProcedure(Timestamp startDt, Timestamp endDt, Integer crnCnt, Boolean isSuccess,
			String msgJson, String crnDataJson) {

		long startTime = System.currentTimeMillis();

		log.info(
				"▶️ Starting PostgreSQL Procedure Execution | procedure={} | startDt={} | endDt={} | crnCnt={} | isSuccess={}",
				PROCEDURE_NAME, startDt, endDt, crnCnt, isSuccess);

		try {

			// =========================================================
			// VALIDATIONS
			// =========================================================

			validateInputs(startDt, endDt, crnCnt, isSuccess, msgJson, crnDataJson);

			log.debug("✅ Input validation completed successfully");

			// =========================================================
			// SQL
			// =========================================================

			final String sql = """
					CALL filecounter.insert_crn(
					    ?,
					    ?,
					    ?,
					    ?,
					    CAST(? AS jsonb),
					    CAST(? AS jsonb)
					)
					""";

			log.debug("📡 Executing procedure SQL");

			// =========================================================
			// EXECUTE PROCEDURE
			// =========================================================

			int rowsAffected = jdbcTemplate.update(sql, startDt, endDt, crnCnt, isSuccess, msgJson, crnDataJson);

			long totalTime = System.currentTimeMillis() - startTime;

			log.info(
					"✅ PostgreSQL Procedure Executed Successfully | procedure={} | rowsAffected={} | executionTime={} ms",
					PROCEDURE_NAME, rowsAffected, totalTime);

			// =========================================================
			// DEBUG LOGS
			// =========================================================

			if (log.isDebugEnabled()) {

				log.debug("Procedure Parameters:");

				log.debug("startDt={}", startDt);

				log.debug("endDt={}", endDt);

				log.debug("crnCnt={}", crnCnt);

				log.debug("isSuccess={}", isSuccess);

				log.debug("msgJson={}", msgJson);

				log.debug("crnDataJson Length={}", crnDataJson != null ? crnDataJson.length() : 0);
			}

		} catch (IllegalArgumentException ex) {

			long totalTime = System.currentTimeMillis() - startTime;

			log.error("❌ Validation Failed While Executing Procedure | procedure={} | executionTime={} ms | error={}",
					PROCEDURE_NAME, totalTime, ex.getMessage(), ex);

			throw ex;

		} catch (DataAccessException ex) {

			long totalTime = System.currentTimeMillis() - startTime;

			log.error("🔥 Database Error While Executing Procedure | procedure={} | executionTime={} ms | error={}",
					PROCEDURE_NAME, totalTime, ex.getMessage(), ex);

			throw new RuntimeException("Database error occurred while executing procedure " + PROCEDURE_NAME, ex);

		} catch (Exception ex) {

			long totalTime = System.currentTimeMillis() - startTime;

			log.error("🔥 Unexpected Error While Executing Procedure | procedure={} | executionTime={} ms | error={}",
					PROCEDURE_NAME, totalTime, ex.getMessage(), ex);

			throw new RuntimeException("Unexpected error occurred while executing procedure " + PROCEDURE_NAME, ex);
		}
	}

	/**
	 * Validates all procedure input parameters.
	 */
	private void validateInputs(Timestamp startDt, Timestamp endDt, Integer crnCnt, Boolean isSuccess, String msgJson,
			String crnDataJson) {

		if (startDt == null) {

			throw new IllegalArgumentException("startDt cannot be null");
		}

		if (endDt == null) {

			throw new IllegalArgumentException("endDt cannot be null");
		}

		if (crnCnt == null) {

			throw new IllegalArgumentException("crnCnt cannot be null");
		}

		if (isSuccess == null) {

			throw new IllegalArgumentException("isSuccess cannot be null");
		}

		if (StringUtils.isBlank(msgJson)) {

			throw new IllegalArgumentException("msgJson cannot be null or empty");
		}

		if (StringUtils.isBlank(crnDataJson)) {

			throw new IllegalArgumentException("crnDataJson cannot be null or empty");
		}

		if (startDt.after(endDt)) {

			throw new IllegalArgumentException("startDt cannot be greater than endDt");
		}
	}

	/**
	 * MAIN METHOD
	 * 
	 * Fetch pending records from DB and execute PostgreSQL procedure
	 */
	public String executeProcedureFromDatabase() {

		long mainStartTime = System.currentTimeMillis();

		log.info("==========================================================");
		log.info("▶️ STARTING CRN PROCEDURE EXECUTION");
		log.info("==========================================================");

		try {

			// =========================================================
			// FETCH PENDING RECORDS
			// =========================================================

			log.info("📡 Fetching pending records from DB where issuccess IS NULL OR FALSE");

			List<ReturnCountCrnJson> pendingRecords = returnCountCrnJsonRepository.findPendingRecords();

			log.info("📦 Total Pending Records Found = {}", pendingRecords.size());

			if (pendingRecords.isEmpty()) {

				log.warn("⚠️ No Pending Records Found For Processing");

				return "No pending records found.";
			}

			// =========================================================
			// PROCESS RECORDS
			// =========================================================

			int successCount = 0;

			int failedCount = 0;

			for (ReturnCountCrnJson record : pendingRecords) {

				long recordStartTime = System.currentTimeMillis();

				try {

					log.info("----------------------------------------------------------");
					log.info("▶️ Processing Record ID = {}", record.getId());
					log.info("----------------------------------------------------------");

					// =====================================================
					// VALIDATE RECORD
					// =====================================================

					validateRecord(record);

					log.info("✅ Record Validation Successful | recordId={}", record.getId());

					// =====================================================
					// CONVERT DATES
					// =====================================================

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime startLocalDateTime = LocalDateTime.parse(record.getStartDt(), formatter);

					LocalDateTime endLocalDateTime = LocalDateTime.parse(record.getEndDt(), formatter);

					Timestamp startDt = Timestamp.valueOf(startLocalDateTime);

					Timestamp endDt = Timestamp.valueOf(endLocalDateTime);

					log.info("🕒 Date Conversion Completed");
					log.debug("Converted startDt={}", startDt);
					log.debug("Converted endDt={}", endDt);

					// =====================================================
					// PREPARE PARAMETERS
					// =====================================================

					Integer crnCnt = record.getCrncnt();

					Boolean isSuccess = record.getIsSuccess();

					// CONVERT STRING TO VALID JSON
					String msgJson = "\"" + record.getMsg() + "\"";

					JsonNode jsonNode = record.getJsonData();

					String crnDataJson = jsonNode != null ? jsonNode.toString() : "{}";

					log.info("📦 Procedure Parameters Prepared");
					log.debug("crnCnt={}", crnCnt);
					log.debug("isSuccess={}", isSuccess);
					log.debug("msgJson={}", msgJson);
					log.debug("crnDataJson Length={}", crnDataJson.length());
					// =====================================================
					// EXECUTE PROCEDURE
					// =====================================================

					executeInsertCrnProcedure(startDt, endDt, crnCnt, isSuccess, msgJson, crnDataJson);

					successCount++;

					long recordExecutionTime = System.currentTimeMillis() - recordStartTime;

					log.info("✅ Record Processed Successfully | recordId={} | executionTime={} ms", record.getId(),
							recordExecutionTime);

				} catch (Exception ex) {

					failedCount++;

					long recordExecutionTime = System.currentTimeMillis() - recordStartTime;

					log.error("❌ Failed To Process Record | recordId={} | executionTime={} ms | error={}",
							record.getId(), recordExecutionTime, ex.getMessage(), ex);
				}
			}

			long totalExecutionTime = System.currentTimeMillis() - mainStartTime;

			log.info("==========================================================");
			log.info("✅ CRN PROCEDURE EXECUTION COMPLETED");
			log.info("📦 Total Records Processed Successfully = {}", successCount);
			log.info("❌ Total Failed Records = {}", failedCount);
			log.info("⏱️ Total Execution Time = {} ms", totalExecutionTime);
			log.info("==========================================================");

			return "Procedure Execution Completed Successfully | Success Count = " + successCount + " | Failed Count = "
					+ failedCount;

		} catch (Exception ex) {

			long totalExecutionTime = System.currentTimeMillis() - mainStartTime;

			log.error("🔥 Unexpected Error While Executing Main Procedure Flow | executionTime={} ms | error={}",
					totalExecutionTime, ex.getMessage(), ex);

			throw new RuntimeException("Unexpected error while executing procedure flow", ex);
		}
	}

	/**
	 * Executes PostgreSQL Procedure
	 */
	
	/**
	 * Validate Entity Record
	 */
	private void validateRecord(ReturnCountCrnJson record) {

		if (record == null) {

			throw new IllegalArgumentException("Record cannot be null");
		}

		if (StringUtils.isBlank(record.getStartDt())) {

			throw new IllegalArgumentException("startDt cannot be null or empty");
		}

		if (StringUtils.isBlank(record.getEndDt())) {

			throw new IllegalArgumentException("endDt cannot be null or empty");
		}

		if (record.getCrncnt() == null) {

			throw new IllegalArgumentException("crncnt cannot be null");
		}

		if (StringUtils.isBlank(record.getMsg())) {

			throw new IllegalArgumentException("msg cannot be null or empty");
		}
	}

	/**
	 * Validate Procedure Inputs
	 */
	
}