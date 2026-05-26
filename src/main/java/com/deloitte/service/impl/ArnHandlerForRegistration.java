package com.deloitte.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.filecounter.GstinEntityRegistration;
import com.deloitte.returns.entity.registration.AlertDetailsRegistration;
import com.deloitte.returns.repository.GstinEntityRegistrationRepository;
import com.deloitte.returns.repository.common.ArnDetailCommonRegistrationRepository;
import com.deloitte.returns.repositoryCommon.AlertDetailsRegistrationRepository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.support.CommonServiceGstrImplSupport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ArnHandlerForRegistration {

	@Autowired
	private RestClientHelper restClient;
	@Autowired
	private CommonServiceGstrImplSupport commonService;
	@Autowired
	private AuthenticationHelper authenticationHelper;
	@Autowired
	private APIDetailsImpl apiDetailsImpl;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	protected AlertDetailsRegistrationRepository alertDetailsRegistrationRepository;

	@Autowired
	protected MasterDataService masterDataService;

	@Autowired
	protected GstUserSessionServices gstUserSessionServices;

	@Autowired
	private ArnDetailCommonRegistrationRepository arnDetailCommonRegistrationRepository;

	@Autowired
	private GstinEntityRegistrationRepository gstinEntityRegistrationRepository;

	@Value("${Arn.file.location}")
	protected String arnUpdateLocation;

	private static final String USERNAME = "GSTG2G18";

	public String getApiConstant() {
		return Constants.GET_REGISTRATION_FILE_DETAIL_ARN_UPDATE_DATA;
	}

	public String processJson() {

		log.info("▶️ [CRN API START] user={}", USERNAME);

		long startTime = System.currentTimeMillis();

		int processed = 0;
		int failed = 0;
		int skipped = 0;

		try {

			// =========================================================
			// FETCH MASTER DATA
			// =========================================================

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {

				log.error("❌ MasterData not found for user={}", USERNAME);

				return "MasterData not found for user : " + USERNAME;
			}

			// =========================================================
			// FETCH SESSION
			// =========================================================

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {

				log.error("❌ Session not authenticated for user={}", USERNAME);

				return "Session not authenticated for user : " + USERNAME;
			}

			// =========================================================
			// FETCH API DETAILS
			// =========================================================

			APIDetails apiDetails = apiDetailsImpl.findByName(getApiConstant());

			if (apiDetails == null) {

				log.error("❌ API Details not found");

				return "API Details not found";
			}

			// =========================================================
			// PREPARE HEADERS
			// =========================================================

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData);

			headers.set("auth-token", session.getAuthToken());
			headers.set("content-type", apiDetails.getApiContentType());

			// =========================================================
			// FETCH PENDING RECORDS
			// CONDITION:
			// isSuccess IS NULL
			// entityId IS NOT NULL
			// =========================================================
			LocalDate partitionDate = LocalDate.of(2026, 4, 30);

			List<AlertDetailsRegistration> pendingRecords = alertDetailsRegistrationRepository
					.findByIsSuccessIsNullAndEntityIdIsNotNullAndCreateDateTimeIsNotNullAndPartitionFyGreaterThanEqual(
							partitionDate);

			if (pendingRecords == null || pendingRecords.isEmpty()) {

				log.warn("⚠️ No pending records found");

				return "NO_PENDING_RECORDS";
			}

			log.info("📦 Total pending records found={}", pendingRecords.size());

			String stateCd = masterData.getStateCd();
			int totalRecords = pendingRecords.size();
			int currentRecord = 0;

			// =========================================================
			// PROCESS RECORDS
			// =========================================================

			for (AlertDetailsRegistration alertObj : pendingRecords) {

				currentRecord++;
				Long alertDetailId = alertObj.getAlertDetailId();
				String alertCd = alertObj.getAlertCd();
				String entityId = alertObj.getEntityId();
				int remainingRecords = totalRecords - currentRecord;

				try {

					log.info("=====================================================");
					log.info("🔄 Processing Record {}/{}", currentRecord, totalRecords);
					log.info("📌 Remaining Records={}", remainingRecords);
					log.info("🔄 ALERT_DETAIL_ID={}", alertDetailId);
					log.info("🔄 ALERT_CD={} ENTITY_ID={}", alertCd, entityId);

					// =========================================================
					// VALIDATE ALERT CODE
					// =========================================================

					if (alertCd == null || !ALLOWED_ALERT_CODES.contains(alertCd)) {

						log.warn("⏭️ Skipping invalid alert code={}", alertCd);

						alertObj.setIsSuccess(false);
						alertObj.setJsonData("{\"message\":\"INVALID_ALERT_CODE\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						skipped++;

						continue;
					}

					// =========================================================
					// BUILD PARAMS
					// =========================================================

					Map<String, String> params = getParams(stateCd, entityId);

					String apiPath = authenticationHelper
							.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

					log.info("📡 Calling API={}", apiPath);

					// =========================================================
					// API CALL
					// =========================================================

					GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

					String responseJson;

					// =========================================================
					// FAILURE CASE
					// =========================================================

					if (response == null || !"1".equals(response.getStatus_cd())) {

						log.error("❌ API FAILED FOR ENTITY_ID={}", entityId);

						alertObj.setIsSuccess(false);

						responseJson = objectMapper.writeValueAsString(response);

						alertObj.setJsonData(responseJson);

						alertDetailsRegistrationRepository.save(alertObj);

						failed++;

						continue;
					}

					// =========================================================
					// SUCCESS CASE
					// =========================================================

					byte[] decoded = Base64.getDecoder().decode(response.getData());

					String decodedJson = new String(decoded, StandardCharsets.UTF_8);

					JsonNode responseNode = objectMapper.readTree(decodedJson);

					responseJson = objectMapper.writeValueAsString(responseNode);

					alertObj.setIsSuccess(true);
					alertObj.setJsonData(responseJson);

					alertDetailsRegistrationRepository.save(alertObj);
					

					log.info("✅ SUCCESS FOR ENTITY_ID={}", entityId);

					processed++;

				} catch (Exception ex) {

					log.error("❌ Error processing ALERT_DETAIL_ID={}", alertDetailId, ex);

					try {

						alertObj.setIsSuccess(false);

						String errorJson = "{\"message\":\"" + ex.getMessage().replace("\"", "'") + "\"}";

						alertObj.setJsonData(errorJson);

						alertDetailsRegistrationRepository.save(alertObj);

					} catch (Exception dbEx) {

						log.error("❌ Failed updating error response in DB", dbEx);
					}

					failed++;
				}
			}

			long totalTime = System.currentTimeMillis() - startTime;

			log.info("=====================================================");
			log.info("🏁 [CRN API END]");
			log.info("✅ Processed={}", processed);
			log.info("❌ Failed={}", failed);
			log.info("⏭️ Skipped={}", skipped);
			log.info("⏱️ Total Time={} ms", totalTime);
			log.info("=====================================================");

			return "SUCCESS | Processed=" + processed + " Failed=" + failed + " Skipped=" + skipped;

		} catch (Exception ex) {

			log.error("❌ CRN API PROCESS FAILED", ex);

			return "FAILED : " + ex.getMessage();
		}
	}

	private Map<String, String> getParams(String stateCd, String entityId) {
		Map<String, String> params = new HashMap<>();
		params.put("action", "ARNUPDATE");
		params.put("state_cd", stateCd);
		params.put("arn", entityId);
		return params;
	}

	private String buildFileName(String startDateTime, String entityId, String alertCd, String statusType) {
		return String.format("%s_%s_%s_%s.json", startDateTime, alertCd, statusType, entityId);
	}

	private String buildFolderPath(String caseType) {
		return Paths.get(arnUpdateLocation, caseType).toString() + File.separator;
	}

	private void upsertGstinEntity(JsonNode jsonData, String entityId, String alertCd, String endDateTime,
			Long returnAlertJsonId, Long returnArnDetailCommonRegistrationId) {

		try {

// ✅ Validate JSON
			if (jsonData == null || jsonData.isEmpty()) {
				log.warn("⚠️ Empty JSON for entityId={}", entityId);
				return;
			}

// ✅ Extract fields
			String arn = jsonData.path("arn").asText(null);
			String gstin = jsonData.path("gstin").asText(null);
			String aplty = jsonData.path("rgdtls").path("aplty").asText(null);

			if (gstin == null || gstin.isBlank()) {
				log.warn("⚠️ GSTIN missing | entityId={} | alertCd={}", entityId, alertCd);
				return;
			}

			String taxpayerType = APLTY_MAPPING.getOrDefault(aplty, "UNKNOWN");

// ✅ Convert new endDateTime to LocalDateTime
			LocalDateTime newEndDate = parseDate(endDateTime);

			Optional<GstinEntityRegistration> optional = gstinEntityRegistrationRepository.findByGstinNumber(gstin);

			if (optional.isPresent()) {

				GstinEntityRegistration existing = optional.get();

				LocalDateTime dbEndDate = parseDate(existing.getEndDateTime());

// 🔥 Compare dates
				if (newEndDate != null && (dbEndDate == null || newEndDate.isAfter(dbEndDate))) {

					existing.setArnNumber(arn);
					existing.setAlertCd(alertCd);
					existing.setEndDateTime(endDateTime);
					existing.setTaxpayerType(taxpayerType);
					existing.setAlertId(returnAlertJsonId);
					existing.setArnDetailCommonRegistrationId(returnArnDetailCommonRegistrationId);

					gstinEntityRegistrationRepository.save(existing);

					log.info("🔄 UPDATED GSTIN={} | oldDate={} | newDate={}", gstin, dbEndDate, newEndDate);

				} else {

					log.info("⏭️ SKIPPED GSTIN={} | DB date newer or equal | dbDate={} | newDate={}", gstin, dbEndDate,
							newEndDate);
				}

			} else {

// 🆕 INSERT
				GstinEntityRegistration entity = new GstinEntityRegistration();

				entity.setGstinNumber(gstin);
				entity.setArnNumber(arn);
				entity.setAlertCd(alertCd);
				entity.setEndDateTime(endDateTime);
				entity.setTaxpayerType(taxpayerType);
				entity.setAlertId(returnAlertJsonId);
				entity.setArnDetailCommonRegistrationId(returnArnDetailCommonRegistrationId);

				gstinEntityRegistrationRepository.save(entity);

				log.info("🆕 INSERTED GSTIN={} | endDate={}", gstin, newEndDate);
			}

		} catch (Exception ex) {
			log.error("❌ Error in upsertGstinEntity | entityId={} | alertCd={}", entityId, alertCd, ex);
		}
	}

	private static final Map<String, String> APLTY_MAPPING = Map.ofEntries(

			// GSTIN
			Map.entry("APLRG", "GSTIN"), Map.entry("APLCR", "GSTIN"), Map.entry("CNREV", "GSTIN"),
			Map.entry("CNREG", "GSTIN"), Map.entry("APLAC", "GSTIN"), Map.entry("APLAN", "GSTIN"),
			Map.entry("APLOC", "GSTIN"), Map.entry("APLWC", "GSTIN"), Map.entry("APLCW", "GSTIN"),

			// GSTPID
			Map.entry("RTTR1", "GSTPID"), Map.entry("CNTRP", "GSTPID"), Map.entry("AGPAC", "GSTPID"),
			Map.entry("AGPAN", "GSTPID"),

			// REGOI
			Map.entry("REGOI", "REGOI"), Map.entry("CNOID", "REGOI"), Map.entry("REGAC", "REGOI"),
			Map.entry("REGAN", "REGOI"),

			// UINR
			Map.entry("APLNR", "UINR"), Map.entry("APEXT", "UINR"), Map.entry("CNNRT", "UINR"),
			Map.entry("ANRAC", "UINR"), Map.entry("ANRAN", "UINR"),

			// UINU
			Map.entry("APLUN", "UINU"), Map.entry("APLEM", "UINU"), Map.entry("APLOT", "UINU"),
			Map.entry("AUNAC", "UINU"), Map.entry("AEMAC", "UINU"), Map.entry("AOTAC", "UINU"),
			Map.entry("AUNAN", "UINU"),

			// UITC
			Map.entry("APLTC", "UITC"), Map.entry("CNTCS", "UITC"), Map.entry("ATCAC", "UITC"),
			Map.entry("ATDAN", "UITC"),

			// UITD
			Map.entry("APLTD", "UITD"), Map.entry("CNTDS", "UITD"), Map.entry("ATDAC", "UITD"),
			Map.entry("ATCAN", "UITD"));

	// 👉 Allowed alert codes
	private static final Set<String> ALLOWED_ALERT_CODES = Set.of("ARG001", "ARG002", "ARG003", "ARG004", "ARG005",
			"ARG006", "ARG007", "ARG008", "ARG009", "ARG010", "ARG011", "ARG012", "ARG013", "ARG014", "ARG016",
			"ARG017", "ARG018", "ARG019", "ARG020", "ARG021", "ARG022", "ARG023", "ARG024", "ARG025", "ARG026",
			"ARG027", "ARG028", "ARG029", "ARG030", "ARG031", "ARG032", "ARG033", "ARG034", "ARG035", "ARG036",
			"ARG037", "ARG038", "ARG039", "ARG040", "ARG041", "ARG042", "ARG043", "ARG044", "ARG045", "ARG046",
			"ARG047", "ARG048", "ARG049", "ARG050", "ARG051", "ARG053", "ARG054", "ARG055", "ARG056", "ARG057",
			"ARG058", "ARG059", "ARG060", "ARG061", "ARG062", "ARG063", "ARG064", "ARG065", "ARG066", "ARG067",
			"ARG069", "ARG070", "ARG071", "ARG072", "ARG073", "ARG074", "ARG075", "ARG076", "ARG080", "ARG081",
			"ARG083", "COMP01", "GSTP01", "GSTP02", "REG001", "REG002", "REG003", "REG004", "REG005", "REG006",
			"REG007", "REG008", "REG009", "REG010", "REG011", "ARG098", "ARG105", "ARG106", "ARG108", "ARG109",
			"ARG110", "ARG111", "ARG112");

	private LocalDateTime parseDate(String dateStr) {
		try {
			if (dateStr == null || dateStr.isBlank())
				return null;

			// your format: yyyy-MM-dd HH:mm:ss
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			return LocalDateTime.parse(dateStr, formatter);

		} catch (Exception e) {
			log.warn("⚠️ Date parsing failed for value={}", dateStr);
			return null;
		}
	}

	public String processRegistration() {

		log.info("▶️ [REGISTRATION API START] user={}", USERNAME);

		long startTime = System.currentTimeMillis();

		int processed = 0;
		int failed = 0;
		int skipped = 0;

		try {

			// =========================================================
			// FETCH MASTER DATA
			// =========================================================

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {

				log.error("❌ MasterData not found for user={}", USERNAME);

				return "MasterData not found for user : " + USERNAME;
			}

			// =========================================================
			// FETCH SESSION
			// =========================================================

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {

				log.error("❌ Session not authenticated for user={}", USERNAME);

				return "Session not authenticated for user : " + USERNAME;
			}

			// =========================================================
			// FETCH RECORDS
			// =========================================================

			LocalDate partitionDate = LocalDate.of(2026, 4, 30);

			List<AlertDetailsRegistration> pendingRecords = alertDetailsRegistrationRepository
					.findByIsSuccessTrueAndIsEntitySuccessIsNullAndEntityJsonIsNullAndPartitionFyGreaterThanEqual(
							partitionDate);

			if (pendingRecords == null || pendingRecords.isEmpty()) {

				log.warn("⚠️ No pending records found");

				return "NO_PENDING_RECORDS";
			}

			log.info("📦 Total pending records found={}", pendingRecords.size());

			int totalRecords = pendingRecords.size();
			int currentRecord = 0;

			// =========================================================
			// PROCESS RECORDS
			// =========================================================

			for (AlertDetailsRegistration alertObj : pendingRecords) {

				currentRecord++;

				Long alertDetailId = alertObj.getAlertDetailId();
				String alertCd = alertObj.getAlertCd();
				String entityId = alertObj.getEntityId();

				int remainingRecords = totalRecords - currentRecord;

				try {

					log.info("=====================================================");
					log.info("🔄 Processing Record {}/{}", currentRecord, totalRecords);
					log.info("📌 Remaining Records={}", remainingRecords);
					log.info("🔄 ALERT_DETAIL_ID={}", alertDetailId);
					log.info("🔄 ALERT_CD={} ENTITY_ID={}", alertCd, entityId);

					// =========================================================
					// GET JSON DATA
					// =========================================================

					String jsonData = alertObj.getJsonData();

					if (jsonData == null || jsonData.isBlank()) {

						log.warn("⚠️ JSON DATA EMPTY");

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"JSON_DATA_EMPTY\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						skipped++;

						continue;
					}

					// =========================================================
					// PARSE JSON
					// =========================================================

					JsonNode rootNode = objectMapper.readTree(jsonData);

					// =========================================================
					// CHECK GSTIN
					// =========================================================

					JsonNode gstinNode = rootNode.get("gstin");

					if (gstinNode == null || gstinNode.asText().isBlank()) {

						log.warn("⚠️ NO GSTIN AVAILABLE");

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"NO_GSTIN_AVAILABLE\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						skipped++;

						continue;
					}

					String gstin = gstinNode.asText();

					log.info("✅ GSTIN FOUND={}", gstin);

					// =========================================================
					// GET rgdtls
					// =========================================================

					JsonNode rgdtlsNode = rootNode.get("rgdtls");

					if (rgdtlsNode == null || !rgdtlsNode.has("aplty")) {

						log.warn("⚠️ APLTY NOT FOUND");

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"APLTY_NOT_FOUND\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						skipped++;

						continue;
					}

					String aplty = rgdtlsNode.get("aplty").asText();

					log.info("📌 APLTY={}", aplty);

					// =========================================================
					// MATCH APLTY
					// =========================================================

					String identityType = APLTY_MAPPING.get(aplty);

					if (identityType == null) {

						log.warn("⚠️ APLTY MAPPING NOT FOUND={}", aplty);

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"APLTY_MAPPING_NOT_FOUND\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						skipped++;

						continue;
					}

					log.info("✅ MATCH FOUND : aplty={} identityType={}", aplty, identityType);

					// =========================================================
					// SAVE INFO
					// =========================================================

					String infoMessage = "MATCH : aplty : " + aplty + " : " + identityType;

					log.info("ℹ️ {}", infoMessage);
					// SAVE INTO COLUMN
					alertObj.setTypeRegistrationMsg(infoMessage);

					// =========================================================
					// FETCH API DETAILS
					// =========================================================

					APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_REGISTRATION_NORMAL_TAX_PAYER);

					if (apiDetails == null) {

						log.error("❌ REGISTRATION API DETAILS NOT FOUND");

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"REGISTRATION_API_DETAILS_NOT_FOUND\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						failed++;

						continue;
					}

					// =========================================================
					// PREPARE HEADERS
					// =========================================================

					HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, session.getAuthToken(),
							apiDetails.getApiContentType());

					// =========================================================
					// BUILD PARAMS
					// =========================================================

					Map<String, String> params = buildRequestParams(masterData, identityType, gstin, apiDetails);

					String uri = authenticationHelper
							.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

					log.info("📡 Calling Registration API FOR GSTIN={}", gstin);
					log.info("📡 Calling Registration API URI={}", uri);
					// =========================================================
					// API CALL
					// =========================================================

					GSTCommonResponseBean response = restClient.get(uri, GSTCommonResponseBean.class, headers);

					// =========================================================
					// NULL RESPONSE
					// =========================================================

					if (response == null) {

						log.error("❌ NULL RESPONSE FOR GSTIN={}", gstin);

						alertObj.setIsEntitySuccess(false);
						alertObj.setEntityJson("{\"message\":\"GSTN_RESPONSE_NULL\"}");

						alertDetailsRegistrationRepository.save(alertObj);

						failed++;

						continue;
					}

					// =========================================================
					// FAILED RESPONSE
					// =========================================================

					if (!"1".equals(response.getStatus_cd())) {

						log.error("❌ FAILED RESPONSE FOR GSTIN={}", gstin);

						alertObj.setIsEntitySuccess(false);

						String failedJson = objectMapper.writeValueAsString(response);

						alertObj.setEntityJson(failedJson);

						alertDetailsRegistrationRepository.save(alertObj);

						failed++;

						continue;
					}

					// =========================================================
					// SUCCESS RESPONSE
					// =========================================================

					byte[] decoded = Base64.getDecoder().decode(response.getData());

					String decodedJson = new String(decoded, StandardCharsets.UTF_8);

					JsonNode responseNode = objectMapper.readTree(decodedJson);

					String responseJson = objectMapper.writeValueAsString(responseNode);

					alertObj.setIsEntitySuccess(true);
					alertObj.setEntityJson(responseJson);

					alertDetailsRegistrationRepository.save(alertObj);

					log.info("✅ REGISTRATION API SUCCESS FOR GSTIN={}", gstin);

					processed++;

				} catch (Exception ex) {

					log.error("❌ Error processing ALERT_DETAIL_ID={}", alertDetailId, ex);

					try {

						alertObj.setIsEntitySuccess(false);

						String errorJson = "{\"message\":\"" + ex.getMessage().replace("\"", "'") + "\"}";

						alertObj.setEntityJson(errorJson);

						alertDetailsRegistrationRepository.save(alertObj);

					} catch (Exception dbEx) {

						log.error("❌ Failed updating entity error response", dbEx);
					}

					failed++;
				}
			}

			long totalTime = System.currentTimeMillis() - startTime;

			log.info("=====================================================");
			log.info("🏁 [REGISTRATION API END]");
			log.info("✅ Processed={}", processed);
			log.info("❌ Failed={}", failed);
			log.info("⏭️ Skipped={}", skipped);
			log.info("⏱️ Total Time={} ms", totalTime);
			log.info("=====================================================");

			return "SUCCESS | Processed=" + processed + " Failed=" + failed + " Skipped=" + skipped;

		} catch (Exception ex) {

			log.error("❌ REGISTRATION API PROCESS FAILED", ex);

			return "FAILED : " + ex.getMessage();
		}
	}

	private Map<String, String> buildRequestParams(MasterData masterData, String identityType, String gstinNumber,
			APIDetails apiDetails) {

		Map<String, String> params = new HashMap<>();

		params.put("action", apiDetails.getApiAction());
		params.put("state_cd", masterData.getStateCd());
		params.put("idty", identityType);
		params.put("id", gstinNumber);

		return params;
	}

}
