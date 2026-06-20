package com.deloitte.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.bean.ProcessResult;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.CommonCrnDate;
import com.deloitte.returns.entity.filecounter.CrnDetailCommon;
import com.deloitte.returns.entity.filecounter.ReturnCountCrnJson;
import com.deloitte.service.abs.AbstractCommonCrnServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommonCrnServiceImpl extends AbstractCommonCrnServiceImpl {

	private static final DateTimeFormatter CRN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
	private static final DateTimeFormatter CRN_FORMATTER_LOCAL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final String USERNAME = "GSTG2G18";

	public String getCrnListByStartAndEndTime(String startDateTime, String endDateTime)
			throws UnsupportedEncodingException {

		log.info("▶️ [CRN API START] user={}  start={} end={}", USERNAME, startDateTime, endDateTime);

		try {

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {
				log.error("❌ MasterData not found for user={}", USERNAME);
				return "MasterData not found for user: " + USERNAME;
			}

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {
				log.error("❌ Session not authenticated for user={}", USERNAME);
				return "Session not authenticated for user: " + USERNAME;
			}

			ReturnCountCrnJson returnCountCrnJson = getReturnFileCountNew(masterData, startDateTime, endDateTime,
					session);

			if (returnCountCrnJson == null || returnCountCrnJson.getCrncnt() == null) {
				log.warn("⚠️ CRN count not found for  start={}", startDateTime);

				return "End Date data Found end date is: " + endDateTime + " not found";
			}

			// CALL THE PROCEDURE FROM HERE

			try {

				Timestamp startTs = Timestamp.valueOf(startDateTime);
				Timestamp endTs = Timestamp.valueOf(endDateTime);

				String msgJson = "{\"message\":\"CRN processed successfully\"}";
				String crnDataJson = returnCountCrnJson.getCrncnt().toString(); // ensure JSON string

				callInsertCrnProcedure.callInsertCrnProcedure(startTs, endTs, returnCountCrnJson.getCrncnt(), true,
						msgJson, crnDataJson);

			} catch (Exception e) {
				log.error("❌ Procedure call failed", e);
			} //
			String response = "CRN Count for  Start: " + startDateTime + ", End: " + endDateTime + " = "
					+ returnCountCrnJson.getCrncnt();

			log.info("🏁 [CRN API END]  start={} result={}", startDateTime, response);

			return "CRN Count: " + returnCountCrnJson.getCrncnt();

		} catch (Exception e) {

			log.error("🔥 Exception occurred while processing CRN API for user={}  error={}", USERNAME, e.getMessage(),
					e);
			return "Error occurred while processing request";
		}
	}

	public String processCrnData() throws UnsupportedEncodingException {

		log.info("▶️ [CRN PROCESS START]");

		String username = "GSTG2G18";
		String lastResponse = null;

		long startTime = System.currentTimeMillis();

		try {

			// ================= LAST END DATE FROM DB =================

			String lastEndDate = returnCountCrnJsonRepository.getLastProcessedEndDt();

			LocalDateTime startDateTime;

			if (lastEndDate != null) {

				startDateTime = LocalDateTime.parse(lastEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				log.info("📌 Last Processed End Date Found In DB :: {}", startDateTime);

			} else {

				startDateTime = LocalDateTime.of(2026, 4, 29, 0, 0);

				log.info("📌 No Previous Data Found. Starting From :: {}", startDateTime);
			}

			// ================= PROCESS TILL YESTERDAY =================

			LocalDateTime yesterdayEnd = LocalDate.now().minusDays(1).atTime(23, 59, 59);

			log.info("📌 Processing Till :: {}", yesterdayEnd);

			while (startDateTime.isBefore(yesterdayEnd)) {

				LocalDateTime endDateTime = startDateTime.plusHours(1);

				String formattedStartDateTime = startDateTime.format(CRN_FORMATTER);

				String formattedEndDateTime = endDateTime.format(CRN_FORMATTER);
				String formattedEndDateTimeLocal = endDateTime.format(CRN_FORMATTER_LOCAL);

				try {

					// ================= END DATE CHECK =================

					boolean alreadyExists = returnCountCrnJsonRepository.existsByEndDt(formattedEndDateTimeLocal);

					if (alreadyExists) {

						log.info("⏭ Skipping Already Processed Slot | startdt={} | enddt={}", formattedStartDateTime,
								formattedEndDateTimeLocal);

						startDateTime = endDateTime;

						continue;
					}

					// ================= API CALL =================

					log.info("📡 Calling GST API | startdt={} | enddt={}", formattedStartDateTime,
							formattedEndDateTime);

					lastResponse = getCrnListByStartAndEndTime(formattedStartDateTime, formattedEndDateTime);

					log.info("✅ API Response Received Successfully | enddt={}", formattedEndDateTime);

				} catch (Exception ex) {

					log.error("❌ Error While Processing Slot | startdt={} | enddt={}", formattedStartDateTime,
							formattedEndDateTime, ex);
				}

				// MOVE TO NEXT HOUR
				startDateTime = endDateTime;
			}

		} catch (Exception e) {

			log.error("❌ CRN Main Process Failed", e);
		}

		long totalTime = System.currentTimeMillis() - startTime;

		log.info("🏁 [CRN PROCESS END] Total Time Taken = {} ms", totalTime);

		return lastResponse;
	}

	public ReturnCountCrnJson getCrnData(String username, String startDateTime, String endDateTime, String caseType)
			throws UnsupportedEncodingException {

		MasterData masterData = masterDataService.getMasterdatabyName(username);
		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(username);

		if (masterData == null || session == null) {
			return null;
		}

		return getReturnFileCountNew(masterData, startDateTime, endDateTime, session);
	}

	public String processAllCaseTypes(int year, int startMonth, int endMonth) throws UnsupportedEncodingException {

		List<String> caseTypes = Arrays.asList("AATUO", "ADJAR", "ADJAS", "ADJAT", "ADJDT", "ADJGP", "ADJND", "ADJNF",
				"ADJPA", "ADJRO", "ADJSA", "ADJSR", "ADJUR", "ADJVP", "ADJWN", "ADJWO", "ADVPD", "AMDPT", "AMPTC",
				"AMYDP", "AMYDT", "AMYGP", "AMYRO", "AMYSA", "AMYTC", "APLTD", "APLTO", "APPEL", "ARAPA", "ARARA",
				"AUDIT", "EBUTO", "EBUTP", "ENPOR", "ENQCS", "IETCS", "LETUT", "RCMOR", "RCPGD", "RCPID", "RFUND",
				"TRAN1", "TRAN2");

		String username = "GSTG2G18";
		String lastResponse = null;

		long overallStartTime = System.currentTimeMillis();

		for (String caseType : caseTypes) {

			log.info("▶️ Starting processing for caseType={}", caseType);

			LocalDateTime startDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0);

			LocalDateTime endDateTime = LocalDateTime.of(year, endMonth,
					LocalDate.of(year, endMonth, 1).lengthOfMonth(), 23, 59);

			while (startDateTime.isBefore(endDateTime)) {

				LocalDateTime nextDateTime = startDateTime.plusHours(1);

				String formattedStartDateTime = startDateTime.format(CRN_FORMATTER);

				String formattedEndDateTime = nextDateTime.format(CRN_FORMATTER);

				startDateTime = nextDateTime;

				try {

					// Duplicate check
					boolean alreadyProcessed = commonCrnDateRepository
							.findByFormattedStartDateTime(formattedStartDateTime).isPresent();

					if (alreadyProcessed) {
						log.debug("⏭ Skipping already processed slot | caseType={} | time={}", caseType,
								formattedStartDateTime);
						continue;
					}

					// API Call
					log.info("📡 Calling API | caseType={} | start={} | end={}", caseType, formattedStartDateTime,
							formattedEndDateTime);

					lastResponse = getCrnListByStartAndEndTime(formattedStartDateTime, formattedEndDateTime);

					// Status check
					String status = (lastResponse != null && !lastResponse.contains("not")) ? "found" : "not found";

					// Save record
					CommonCrnDate entity = new CommonCrnDate();
					entity.setFormattedStartDateTime(formattedStartDateTime);
					entity.setStatus(status);

					commonCrnDateRepository.save(entity);

					log.info("💾 Saved | caseType={} | time={} | status={}", caseType, formattedStartDateTime, status);

				} catch (Exception ex) {
					log.error("❌ Error | caseType={} | time={}", caseType, formattedStartDateTime, ex);
				}
			}

			log.info("✅ Completed processing for caseType={}", caseType);
		}

		long totalTime = System.currentTimeMillis() - overallStartTime;

		log.info("🏁 ALL CASE TYPES COMPLETED | Total Time={} ms", totalTime);

		return "All case types processed successfully";
	}

	private ReturnCountCrnJson getReturnFileCountNew(MasterData masterData, String startDateTime, String endDateTime,
			GSTUserSession gstUserSessions) {

		ReturnCountCrnJson returnCountCrnJson = new ReturnCountCrnJson();

		try {

			log.info("=====================================================");
			log.info("▶️ Calling CRN File Count API");
			log.info("📌 Start={} End={}", startDateTime, endDateTime);
			log.info("=====================================================");

			// =====================================================
			// API DETAILS
			// =====================================================

			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL_CRN_lIST);

			if (apiDetails == null) {

				log.error("❌ API details not found");

				returnCountCrnJson.setIsSuccess(false);

				returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", "API_DETAILS_NOT_FOUND")));

				return returnCountCrnJson;
			}

			// =====================================================
			// HEADERS
			// =====================================================

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, gstUserSessions.getAuthToken(),
					apiDetails.getApiContentType());

			// =====================================================
			// PARAMS
			// =====================================================

			Map<String, String> params = getParamsForGetReturnFileCount(apiDetails, masterData, startDateTime,
					endDateTime);

			String apiPath = authenticationHelper
					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

			log.info("📡 API PATH={}", apiPath);

			// =====================================================
			// API CALL
			// =====================================================

			GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

			// =====================================================
			// DATE FORMAT
			// =====================================================

			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");

			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime startDt = LocalDateTime.parse(startDateTime, inputFormatter);

			LocalDateTime endDt = LocalDateTime.parse(endDateTime, inputFormatter);

			returnCountCrnJson.setStartDt(startDt.format(outputFormatter));

			returnCountCrnJson.setEndDt(endDt.format(outputFormatter));

			returnCountCrnJson.setActivityDt(LocalDateTime.now());

			// returnCountCrnJson.setUrl(apiPath);

			// =====================================================
			// NULL RESPONSE
			// =====================================================

			if (response == null) {

				log.error("❌ Null response from API");

				returnCountCrnJson.setIsSuccess(false);

				returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", "NULL_RESPONSE")));

				returnCountCrnJson.setUrl(apiPath);

				return returnCountCrnJsonRepository.save(returnCountCrnJson);
			}

			// =====================================================
			// FAILURE RESPONSE
			// =====================================================

			if ("0".equalsIgnoreCase(response.getStatus_cd())) {

				log.error("❌ API Returned Failure Response :: {}", response);

				String errorMessage = "Unknown Error";

				if (response.getError() != null && response.getError().get("message") != null) {

					errorMessage = response.getError().get("message");
				}

				returnCountCrnJson.setIsSuccess(false);

				returnCountCrnJson.setCrncnt(0);

				returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", errorMessage)));

				returnCountCrnJson.setUrl(apiPath);

				return returnCountCrnJsonRepository.save(returnCountCrnJson);
			}

			// =====================================================
			// SUCCESS RESPONSE
			// =====================================================

			if ("1".equalsIgnoreCase(response.getStatus_cd())) {

				returnCountCrnJson.setIsSuccess(true);

				if (response.getData() != null) {

					byte[] decodedData = Base64.getDecoder().decode(response.getData());

					String decodedJson = new String(decodedData, StandardCharsets.UTF_8);

					JsonNode jsonNode = objectMapper.readTree(decodedJson);

					returnCountCrnJson.setJsonData(jsonNode);

					Long count = jsonNode.path("crncnt").asLong(0);

					returnCountCrnJson.setCrncnt(count.intValue());

					returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", "SUCCESS")));

					// =============================================
					// SAVE CRN COMMON
					// =============================================

					ReturnCountCrnJson savedCrn = returnCountCrnJsonRepository.save(returnCountCrnJson);

					log.info("✅ CRN COMMON SAVED ID={}", savedCrn.getId());

					// =============================================
					// GET CRN LIST
					// =============================================

					JsonNode crnListNode = jsonNode.path("crnlist");

					if (crnListNode.isArray() && crnListNode.size() > 0) {

						log.info("📦 Total CRN Records Received={}", crnListNode.size());

						List<CrnDetailCommon> detailList = new ArrayList<>();

						int processedCounter = 0;

						for (JsonNode details : crnListNode) {

							try {

								processedCounter++;

								log.info("🔄 Processing CRN Record {}/{}", processedCounter, crnListNode.size());

								CrnDetailCommon detail = new CrnDetailCommon();

								detail.setIdReturnCountCrnJson(savedCrn.getId());

								detail.setCrn(details.path("crn").asText(null));

								detail.setOriginalStatus(details.path("status").asText(null));

								detail.setOriginalCaseTyp(details.path("casetyp").asText(null));

								detail.setOriginalApprovAuth(details.path("approvAuth").asText(null));

								detail.setOriginalUpdateTmstmp(details.path("updateTmstmp").asText(null));

								//detail.setJsonData(details);

								detail.setIsProcessed(false);

								detail.setIsSuccess(null);

								//detail.setUrl(apiPath);

								// IMPORTANT FOR JSONB COLUMN
								detail.setMsg(objectMapper.valueToTree(Map.of("message", "SUCCESS")));

								detailList.add(detail);

							} catch (Exception innerEx) {

								log.error("❌ Failed parsing CRN detail record", innerEx);
							}
						}

						// =========================================
						// BATCH INSERT
						// =========================================

						if (!detailList.isEmpty()) {

							crnDetailCommonRepository.saveAll(detailList);

							log.info("✅ CRN DETAIL INSERTED COUNT={}", detailList.size());
						}

						log.info("🏁 Total Processed CRN Records={}", processedCounter);

					} else {

						log.warn("⚠️ No CRN list found");
					}

					log.info("✅ CRN COUNT={}", count);

					return savedCrn;
				}
			}

			// =====================================================
			// UNKNOWN RESPONSE
			// =====================================================

			returnCountCrnJson.setIsSuccess(false);

			returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", "UNKNOWN_RESPONSE")));

			return returnCountCrnJsonRepository.save(returnCountCrnJson);

		} catch (Exception ex) {

			log.error("🔥 Exception while calling CRN File Count API | start={} | error={}", startDateTime,
					ex.getMessage(), ex);

			try {

				returnCountCrnJson.setIsSuccess(false);

				returnCountCrnJson.setMsg(objectMapper.writeValueAsString(Map.of("message", ex.getMessage())));

				returnCountCrnJson.setActivityDt(LocalDateTime.now());

				returnCountCrnJsonRepository.save(returnCountCrnJson);

			} catch (Exception dbEx) {

				log.error("❌ Failed saving exception response", dbEx);
			}
		}

		return returnCountCrnJson;
	}

//	private ReturnCountCrnJson getReturnFileCountNew(MasterData masterData, String startDateTime, String endDateTime,
//			GSTUserSession gstUserSessions) {
//
//		ReturnCountCrnJson returnCountCrnJson = new ReturnCountCrnJson();
//
//		try {
//
//			log.info("▶️ Calling CRN File Count API |  start={} | end={}", startDateTime, endDateTime);
//
//			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL_CRN_lIST);
//
//			if (apiDetails == null) {
//
//				log.error("❌ API details not found");
//				return returnCountCrnJson;
//			}
//
//			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, gstUserSessions.getAuthToken(),
//					apiDetails.getApiContentType());
//
//			Map<String, String> params = getParamsForGetReturnFileCount(apiDetails, masterData, startDateTime,
//					endDateTime);
//
//			String apiPath = authenticationHelper
//					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);
//
//			log.debug("📡 CRN File Count API Path={}", apiPath);
//
//			GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);
//
//			// CONVERT DATE
//			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
//			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//			LocalDateTime startDt = LocalDateTime.parse(startDateTime, inputFormatter);
//			LocalDateTime endDt = LocalDateTime.parse(endDateTime, inputFormatter);
//
//			returnCountCrnJson.setStartDt(startDt.format(outputFormatter));
//			returnCountCrnJson.setEndDt(endDt.format(outputFormatter));
//			returnCountCrnJson.setActivityDt(LocalDateTime.now());
//
//			if (response == null) {
//
//				log.error("❌ Null response from API");
//
//				returnCountCrnJson.setIsSuccess(false);
//				returnCountCrnJson.setMsg("Null Response");
//				returnCountCrnJson.setUrl(apiPath);
//
//				returnCountCrnJsonRepository.save(returnCountCrnJson);
//
//				return returnCountCrnJson;
//			}
//
//			if ("0".equalsIgnoreCase(response.getStatus_cd())) {
//
//				log.error("❌ API Returned Failure Response :: {}", response);
//
//				String errorMessage = "Unknown Error";
//
//				if (response.getError() != null && response.getError().get("message") != null) {
//
//					errorMessage = response.getError().get("message");
//				}
//
//				returnCountCrnJson.setIsSuccess(false);
//				returnCountCrnJson.setMsg(errorMessage);
//				returnCountCrnJson.setUrl(apiPath);
//
//				returnCountCrnJsonRepository.save(returnCountCrnJson);
//
//				return returnCountCrnJson;
//			}
//
//			// ---------- SUCCESS ----------
//			if ("1".equals(response.getStatus_cd())) {
//
//				returnCountCrnJson.setIsSuccess(true);
//
//				if (response.getData() != null) {
//
//					byte[] decodedData = Base64.getDecoder().decode(response.getData());
//
//					String decodedJson = new String(decodedData, StandardCharsets.UTF_8);
//
//					log.debug("📄 Decoded CRN JSON={}", decodedJson);
//
//					JsonNode jsonNode = objectMapper.readTree(decodedJson);
//
//					// Save JSON
//					returnCountCrnJson.setJsonData(jsonNode);
//					returnCountCrnJson.setMsg("SUCCESS");
//
//					Long count = jsonNode.path("crncnt").asLong();
//					returnCountCrnJson.setCrncnt(count.intValue());
//					// Save JSON log
//					returnCountCrnJsonRepository.save(returnCountCrnJson);
//
//					log.info("✅ CRN Count received={}", count);
//				}
//				
//			}
//
//			// ---------- FAILURE ----------
//			else {
//
//				returnCountCrnJson.setIsSuccess(false);
//
//				String errorMessage = "Unknown error";
//
//				if (response.getError() != null) {
//					errorMessage = response.getError().get("message");
//				}
//
//				returnCountCrnJson.setMsg(errorMessage);
//
//				log.error("❌ GST API error | {}", errorMessage);
//
//				log.debug("Full API Response={}", response);
//				// Save JSON log
//				returnCountCrnJsonRepository.save(returnCountCrnJson);
//			}
//
//		
//
//		} catch (Exception ex) {
//
//			log.error("🔥 Exception while calling CRN File Count API | start={} | error={}", startDateTime,
//					ex.getMessage(), ex);
//
//			returnCountCrnJson.setIsSuccess(false);
//			returnCountCrnJson.setMsg(ex.getMessage());
//			returnCountCrnJson.setActivityDt(LocalDateTime.now());
//
//			returnCountCrnJsonRepository.save(returnCountCrnJson);
//		}
//
//		return returnCountCrnJson;
//	}

	private Map<String, String> getParamsForGetReturnFileCount(APIDetails apiDetails, MasterData masterData,
			String startDateTime, String endDateTime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "CRNLST");
		params.put("state_cd", masterData.getStateCd());
		params.put("start_tm", startDateTime);
		params.put("end_tm", endDateTime);
		params.put("iseod", "N");
		return params;
	}

	@Override
	protected String getApiConstant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public String processAllCaseTypesInBatch(int year, int startMonth, int endMonth)
			throws UnsupportedEncodingException {

		List<String> caseTypes = Arrays.asList("AATUO", "ADJAR", "ADJAS", "ADJAT", "ADJDT", "ADJGP", "ADJND", "ADJNF",
				"ADJPA", "ADJRO", "ADJSA", "ADJSR", "ADJUR", "ADJVP", "ADJWN", "ADJWO", "ADVPD", "AMDPT", "AMPTC",
				"AMYDP", "AMYDT", "AMYGP", "AMYRO", "AMYSA", "AMYTC", "APLTD", "APLTO", "APPEL", "ARAPA", "ARARA",
				"AUDIT", "EBUTO", "EBUTP", "ENPOR", "ENQCS", "IETCS", "LETUT", "RCMOR", "RCPGD", "RCPID", "RFUND",
				"TRAN1", "TRAN2");

		String username = "GSTG2G18";

		long overallStartTime = System.currentTimeMillis();

		int totalSuccess = 0;
		int totalSkipped = 0;
		int totalFailed = 0;

		for (String caseType : caseTypes) {

			log.info("========================================");
			log.info("Starting caseType: {}", caseType);
			log.info("========================================");

			try {
				ProcessResult result = processSingleCaseType(username, caseType, year, startMonth, endMonth);

				totalSuccess += result.getSuccessCount();
				totalSkipped += result.getSkippedCount();
				totalFailed += result.getFailedCount();

				// help GC
				System.gc();

				// optional cooldown
				Thread.sleep(1000);

			} catch (Exception e) {
				totalFailed++;
				log.error("Error processing caseType={}", caseType, e);
			}
		}

		long totalTime = System.currentTimeMillis() - overallStartTime;

		return "Completed All CaseTypes | Success=" + totalSuccess + " Skipped=" + totalSkipped + " Failed="
				+ totalFailed + " TimeTaken=" + totalTime + " ms";
	}

	private ProcessResult processSingleCaseType(String username, String caseType, int year, int startMonth,
			int endMonth) throws UnsupportedEncodingException {

		int successCount = 0;
		int skippedCount = 0;
		int failedCount = 0;

		LocalDateTime startDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0);

		LocalDateTime endDateTime = LocalDateTime.of(year, endMonth, LocalDate.of(year, endMonth, 1).lengthOfMonth(),
				23, 59);

		while (startDateTime.isBefore(endDateTime)) {

			LocalDateTime nextDateTime = startDateTime.plusHours(1);

			String formattedStart = startDateTime.format(CRN_FORMATTER);

			String formattedEnd = nextDateTime.format(CRN_FORMATTER);

			startDateTime = nextDateTime;

			try {

// duplicate check
				boolean alreadyProcessed = commonCrnDateRepository.findByFormattedStartDateTime(formattedStart)
						.isPresent();

				if (alreadyProcessed) {
					skippedCount++;
					log.debug("Skipping duplicate {} {}", caseType, formattedStart);
					continue;
				}

				log.info("Calling API | caseType={} | start={}", caseType, formattedStart);

				String response = getCrnListByStartAndEndTime(formattedStart, formattedEnd);

				String status = (response != null && !response.contains("not")) ? "found" : "not found";

				CommonCrnDate entity = new CommonCrnDate();
				entity.setFormattedStartDateTime(formattedStart);
				entity.setStatus(status);

				commonCrnDateRepository.save(entity);

				successCount++;

// clear large response object
				response = null;

			} catch (Exception e) {
				failedCount++;

				log.error("Failed | caseType={} | time={}", caseType, formattedStart, e);
			}
		}

		log.info("Completed caseType={} success={} skipped={} failed={}", caseType, successCount, skippedCount,
				failedCount);

		return new ProcessResult(successCount, skippedCount, failedCount);
	}

	public String processDateAccordingToStartAndEndMonth(int year, int startMonth, int endMonth)
			throws UnsupportedEncodingException {

		log.info("▶️ [CRN PROCESS START]  year={}, startMonth={}, endMonth={}", year, startMonth, endMonth);

		String username = "GSTG2G18";
		String lastResponse = null;

		LocalDateTime startDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0);

		LocalDateTime endDateTime = LocalDateTime.of(year, endMonth, LocalDate.of(year, endMonth, 1).lengthOfMonth(),
				23, 59);

		long startTime = System.currentTimeMillis();

		while (startDateTime.isBefore(endDateTime)) {

			LocalDateTime nextDateTime = startDateTime.plusHours(1);

			String formattedStartDateTime = startDateTime.format(CRN_FORMATTER);
			String formattedEndDateTime = nextDateTime.format(CRN_FORMATTER);

			startDateTime = nextDateTime;

			try {

				// ================= DUPLICATE CHECK =================

				boolean alreadyProcessed = commonCrnDateRepository.findByFormattedStartDateTime(formattedStartDateTime)
						.isPresent();

				if (alreadyProcessed) {
					log.debug("⏭ Skipping already processed time slot |  startTime={}", formattedStartDateTime);
					continue;
				}

				// ================= GST API CALL =================
				log.info("📡 Calling CRN API |  start={} | end={}", formattedStartDateTime, formattedEndDateTime);

				lastResponse = getCrnListByStartAndEndTime(formattedStartDateTime, formattedEndDateTime);

				// ================= STATUS DECISION =================
				String status = (lastResponse != null && !lastResponse.contains("not")) ? "found" : "not found";

				// ================= SAVE RECORD =================
				CommonCrnDate entity = new CommonCrnDate();
				entity.setFormattedStartDateTime(formattedStartDateTime);
				entity.setStatus(status);

				commonCrnDateRepository.save(entity);

				log.info("💾 CRN time slot saved |  startTime={} | status={}", formattedStartDateTime, status);

			} catch (Exception ex) {

				log.error("❌ Error processing time slot |  startTime={}", formattedStartDateTime, ex);
			}
		}

		long totalTime = System.currentTimeMillis() - startTime;

		log.info("🏁 [CRN PROCESS END]  TimeTaken={} ms", totalTime);

		return lastResponse;
	}

	// =========================================================
	// AUTO HOURLY CRN PROCESS
	// PRODUCTION READY
	// =========================================================

	public String processCrnAutomatically() {

		log.info("=====================================================");
		log.info("▶️ [AUTO CRN PROCESS START]");
		log.info("=====================================================");

		long overallStartTime = System.currentTimeMillis();

		int processed = 0;
		int failed = 0;
		int skipped = 0;

		try {
			
		     // =========================================================
	        // DELETE INVALID RECORDS
	        // =========================================================

	        int deletedCount = returnCountCrnJsonRepository.deleteRecordsWhereStartDtIsNull();

	        if (deletedCount > 0) {

	            log.warn("🗑️ Deleted {} invalid records where startdt is NULL", deletedCount);

	        } else {

	            log.info("✅ No invalid records found with startdt NULL");
	        }

			// =========================================================
			// GET LAST RECORD
			// =========================================================

			ReturnCountCrnJson lastRecord = returnCountCrnJsonRepository.findTopByOrderByEndDtDesc();

			LocalDateTime startDateTime;

			// =========================================================
			// FIRST TIME EXECUTION
			// =========================================================

			if (lastRecord == null || lastRecord.getEndDt() == null) {

				log.warn("⚠️ No previous CRN record found");

				// FIXED START DATE
				startDateTime = LocalDateTime.of(2026, 5, 1, 0, 0);

			} else {

				startDateTime = LocalDateTime.parse(lastRecord.getEndDt(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				log.info("📌 Last Processed End Time={}", startDateTime);
			}

			// =========================================================
			// PROCESS ONLY TILL YESTERDAY 00:00
			// =========================================================

			LocalDateTime maxAllowedDateTime = LocalDate.now().minusDays(0).atStartOfDay();

			log.info("📌 Max Allowed End Time={}", maxAllowedDateTime);

			// =========================================================
			// NO NEW DATA
			// =========================================================

			if (!startDateTime.isBefore(maxAllowedDateTime)) {

				log.warn("⚠️ No new CRN data available");

				return "NO_NEW_DATA_AVAILABLE";
			}

			// =========================================================
			// FETCH MASTER DATA
			// =========================================================

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {

				log.error("❌ MasterData not found");

				return "MASTERDATA_NOT_FOUND";
			}

			// =========================================================
			// FETCH SESSION
			// =========================================================

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {

				log.error("❌ Session not authenticated");

				return "SESSION_NOT_FOUND";
			}

			// =========================================================
			// LOOP HOUR BY HOUR
			// =========================================================

			while (startDateTime.isBefore(maxAllowedDateTime)) {

				LocalDateTime endDateTime = startDateTime.plusHours(1);

				try {

					log.info("-----------------------------------------------------");
					log.info("🔄 Processing Window");
					log.info("📌 Start Time={}", startDateTime);
					log.info("📌 End Time={}", endDateTime);
					log.info("-----------------------------------------------------");

					String startDate = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm"));

					String endDate = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm"));

					// =========================================================
					// CALL API
					// =========================================================

					ReturnCountCrnJson response = getReturnFileCountNew(masterData, startDate, endDate, session);

					// =========================================================
					// NULL RESPONSE
					// =========================================================

					if (response == null) {

						log.error("❌ Null response received");

						failed++;

						startDateTime = endDateTime;

						continue;
					}

					// =========================================================
					// SUCCESS
					// =========================================================

					log.info("✅ CRN COUNT={}", response.getCrncnt());

					processed++;

				} catch (Exception ex) {

					log.error("❌ Error processing hourly CRN", ex);

					failed++;
				}

				// =========================================================
				// MOVE NEXT HOUR
				// =========================================================

				startDateTime = endDateTime;
			}

			long totalTime = System.currentTimeMillis() - overallStartTime;

			log.info("=====================================================");
			log.info("🏁 [AUTO CRN PROCESS END]");
			log.info("✅ Processed={}", processed);
			log.info("❌ Failed={}", failed);
			log.info("⏭️ Skipped={}", skipped);
			log.info("⏱️ Total Time={} ms", totalTime);
			log.info("=====================================================");

			return "SUCCESS | Processed=" + processed + " Failed=" + failed + " Skipped=" + skipped;

		} catch (Exception ex) {

			log.error("❌ AUTO CRN PROCESS FAILED", ex);

			return "FAILED : " + ex.getMessage();
		}
	}
}
