package com.deloitte.service.support;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
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
import com.deloitte.common.entity.ArnDetailCommonRegistration;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.AlertJson;
import com.deloitte.returns.entity.GstinEntityRegistration;
import com.deloitte.returns.repository.GstinEntityRegistrationRepository;
import com.deloitte.returns.repository.common.ArnDetailCommonRegistrationRepository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Service("ARNUPDATE")
@Log4j2
public class ArnUpdateHandlerServiceImpl implements ArnUpdateHandler {

	@Autowired
	private MasterDataService masterDataService;
	@Autowired
	private GstUserSessionServices gstUserSessionServices;
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
	private ArnDetailCommonRegistrationRepository arnDetailCommonRegistrationRepository;

	@Autowired
	private GstinEntityRegistrationRepository gstinEntityRegistrationRepository;

	@Value("${Arn.file.location}")
	protected String arnUpdateLocation;

	public String getApiConstant() {
		return Constants.GET_REGISTRATION_FILE_DETAIL_ARN_UPDATE_DATA;
	}

	@Override
	public String processJson(AlertJson returnAlertJson, MasterData masterData, String startDateTime,
			String endDateTime, GSTUserSession gstUserSessions, String strYear) {

		log.info("🚀 ARNUPDATE processing started | year={} | start={} | end={}", strYear, startDateTime, endDateTime);

		long startTime = System.currentTimeMillis();

		try {
			APIDetails apiDetails = apiDetailsImpl.findByName(getApiConstant());

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData);
			headers.set("auth-token", gstUserSessions.getAuthToken());
			headers.set("content-type", apiDetails.getApiContentType());

			JsonNode rootNode = returnAlertJson.getJsonData();

			if (rootNode == null || !rootNode.has("alerts")) {
				log.warn("⚠️ No alerts found in JSON");
				return "NO_DATA";
			}

			JsonNode alerts = rootNode.get("alerts");

			if (alerts.isEmpty()) {
				log.warn("⚠️ Alerts array is empty");
				return "NO_ALERTS";
			}

			String stateCd = masterData.getStateCd();
			String formattedStartDate = startDateTime.replace(":", "_");

			int processed = 0;
			int skipped = 0;
			int failed = 0;

			for (JsonNode node : alerts) {

				// ✅ Safe extraction
				String alertCd = node.hasNonNull("alertCd") ? node.get("alertCd").asText() : null;
				String entityId = node.hasNonNull("entityId") ? node.get("entityId").asText() : null;
				String entityTyp = node.hasNonNull("entityTyp") ? node.get("entityTyp").asText() : "UNKNOWN";
				String insertTime = node.hasNonNull("insert_tm") ? node.get("insert_tm").asText() : "NA";

				// 🚨 HARD VALIDATION
				if (alertCd == null || alertCd.isBlank()) {
					log.warn("⚠️ Skipping record due to missing alertCd: {}", node);
					skipped++;
					continue;
				}

				if (entityId == null || entityId.isBlank()) {
					log.warn("⚠️ Skipping alertCd={} due to missing entityId", alertCd);
					skipped++;
					continue;
				}

				// ✅ Filter allowed alert codes
				if (!ALLOWED_ALERT_CODES.contains(alertCd)) {
					log.debug("⏭️ Skipped alertCd={} (not allowed)", alertCd);
					skipped++;
					continue;
				}

				try {
					Map<String, String> params = getParams(stateCd, entityId);

					String apiPath = authenticationHelper
							.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

					GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

					String statusType;
					String url;
					JsonNode responseJson;

					if (response == null || !"1".equals(response.getStatus_cd())) {
						statusType = "FAIL";
						url = apiPath;
						responseJson = objectMapper.valueToTree(response);
						failed++;
					} else {
						byte[] decoded = Base64.getDecoder().decode(response.getData());
						responseJson = objectMapper.readTree(new String(decoded, StandardCharsets.UTF_8));
						statusType = "SUCCESS";
						url = "SUCCESS";
						processed++;
					}

					String fileName = buildFileName(formattedStartDate, entityId, alertCd, statusType);
					String folderPath = buildFolderPath("ARNUPDATE");

					saveFileDetails(fileName, folderPath, startDateTime, endDateTime, entityId, statusType,
							responseJson, url, alertCd, entityTyp, insertTime, returnAlertJson);

				} catch (Exception ex) {
					failed++;
					log.error("❌ Error entityId={} alertCd={}", entityId, alertCd, ex);
				}
			}

			long totalTime = System.currentTimeMillis() - startTime;

			log.info("✅ ARNUPDATE Completed | processed={} | skipped={} | failed={} | time={} ms", processed, skipped,
					failed, totalTime);

			return "SUCCESS";

		} catch (Exception ex) {
			log.error("🔥 Fatal error in ARNUPDATE", ex);
			return "FAILED";
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

	private void saveFileDetails(String fileName, String folderPath, String startDateTime, String endDateTime,
			String entityId, String statusType, JsonNode jsonData, String url, String alertCd, String entityTyp,
			String insertTime, AlertJson returnAlertJson) {

		try {
			File directory = new File(folderPath);
			if (!directory.exists())
				directory.mkdirs();

			File file = new File(directory, fileName);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonData);

			log.debug("📄 File saved: {}", file.getAbsolutePath());

			ArnDetailCommonRegistration arnDetailCommonRegistration = new ArnDetailCommonRegistration();
			arnDetailCommonRegistration.setAlertCd(alertCd);
			arnDetailCommonRegistration.setEndDateTime(endDateTime);
			arnDetailCommonRegistration.setEntityIdOrArn(entityId);
			arnDetailCommonRegistration.setEntityTyp(entityTyp);
			arnDetailCommonRegistration.setFileName(fileName);
			arnDetailCommonRegistration.setFolderPath(folderPath);
			arnDetailCommonRegistration.setInsertTime(insertTime);
			arnDetailCommonRegistration.setIsProcessed(false);
			arnDetailCommonRegistration.setJsonData(jsonData);
			arnDetailCommonRegistration.setStartDateTime(startDateTime);
			arnDetailCommonRegistration.setStatusType(statusType);
			arnDetailCommonRegistration.setUrl(url);
			arnDetailCommonRegistration.setReturnAlertJsonId(returnAlertJson.getId());

			ArnDetailCommonRegistration returnArnDetailCommonRegistration = arnDetailCommonRegistrationRepository
					.save(arnDetailCommonRegistration);
			upsertGstinEntity(jsonData, entityId, alertCd, endDateTime, returnAlertJson.getId(),
					returnArnDetailCommonRegistration.getId());
			// 👉 DB GstinEntity write here please

			log.info("💾 Saved entityId={} alertCd={}", entityId, alertCd);

		} catch (IOException e) {
			log.error("❌ File write error: {}", fileName, e);
		}
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

}