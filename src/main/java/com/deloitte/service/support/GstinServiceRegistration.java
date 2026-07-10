package com.deloitte.service.support;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.GstinEntity;
import com.deloitte.returns.entity.DownloadDocument.DcupdtlsGstr9c;
import com.deloitte.returns.entity.regis.RegistrationDataJsonFile;
import com.deloitte.returns.entity.regis.RegistrationDataJsonFileView;
import com.deloitte.returns.entity.regis.RegistrationNormalTaxPayer;
import com.deloitte.returns.entity.registds.RegistrationTdsTcs;
import com.deloitte.returns.entity.registration.AlertDetailsRegistration;
import com.deloitte.returns.entity.registration.AlertRegistration;
import com.deloitte.returns.entity.registration.RegDocuments;
import com.deloitte.returns.entity.registration.RentActivePpbzdtls;
import com.deloitte.service.abs.CommonServiceImplAbs;
import com.deloitte.service.utility.SftpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GstinServiceRegistration extends CommonServiceImplAbs {

	private final CommonControllerGstrUtilityImpl commonControllerGstrUtilityImpl;

	private static final String USERNAME = "GSTG2G18";
	private static final String GSTIN_IDENTITY = "GSTIN";
	private static final DateTimeFormatter ALERT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
	private static final DateTimeFormatter ALERT_FORMATTER_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");

	private static final String BASE_PATH_DW = "/database/GST_FILES/Return_Auto/dw";

	GstinServiceRegistration(CommonControllerGstrUtilityImpl commonControllerGstrUtilityImpl) {
		this.commonControllerGstrUtilityImpl = commonControllerGstrUtilityImpl;
	}

	public String processAlertAutomatically() throws UnsupportedEncodingException {

		log.info("▶️ [AUTO ALERT PROCESS START]");

		String lastResponse = null;

		// =====================================================
		// GET LAST PROCESSED RECORD
		// =====================================================
		int deletedCount = alertRegistrationRepository.deleteRecordsWhereStartTmIsNull();
		log.info("Deleted records count={}", deletedCount);
		Optional<AlertRegistration> optionalAlert = alertRegistrationRepository.getLastProcessedAlert();

		LocalDateTime currentStartDateTime;

		if (optionalAlert.isPresent()) {

			// START FROM LAST END TIME
			currentStartDateTime = optionalAlert.get().getEndTm();

			log.info("📌 Last processed end time found: {}", currentStartDateTime);

		} else {

			// FIRST TIME RUN
			currentStartDateTime = LocalDateTime.of(2026, 5, 15, 0, 0, 0);

			log.info("📌 No previous data found. Starting from default date: {}", currentStartDateTime);
		}

		// =====================================================
		// END TIME = TODAY - 1
		// =====================================================

		LocalDate yesterday = LocalDate.now().minusDays(1);

		LocalDateTime finalEndDateTime = LocalDateTime.of(yesterday, LocalTime.of(23, 59, 59));

		log.info("📌 Final end datetime: {}", finalEndDateTime);

		long processStartTime = System.currentTimeMillis();

		// =====================================================
		// LOOP HOUR BY HOUR
		// =====================================================

		while (!currentStartDateTime.isAfter(finalEndDateTime)) {

			LocalDateTime currentEndDateTime = currentStartDateTime.plusHours(1);

			String formattedStartDateTime = currentStartDateTime.format(ALERT_FORMATTER);

			String formattedEndDateTime = currentEndDateTime.format(ALERT_FORMATTER);

			try {

				String year = String.valueOf(currentStartDateTime.getYear());

				log.info("📡 Calling ALERT API | start={} | end={}", formattedStartDateTime, formattedEndDateTime);

				lastResponse = getAlertListByStartAndEndTime(USERNAME, formattedStartDateTime, formattedEndDateTime,
						year);

				String status = (lastResponse != null && lastResponse.contains("SUCCESS")) ? "FOUND" : "NOT_FOUND";

				log.info("💾 ALERT processed successfully | start={} | status={}", formattedStartDateTime, status);

			} catch (Exception ex) {

				log.error("❌ Error processing ALERT | startTime={}", formattedStartDateTime, ex);
			}

			// MOVE NEXT HOUR
			currentStartDateTime = currentStartDateTime.plusHours(1);
		}

		long totalTime = System.currentTimeMillis() - processStartTime;

		log.info("🏁 [AUTO ALERT PROCESS END] totalTime={} ms", totalTime);

		return lastResponse;
	}

	private String getAlertListByStartAndEndTime(String username, String startDateTime, String endDateTime,
			String strYear) {

		log.info("▶️ [CRN API START] user={} Year={} start={} end={}", username, strYear, startDateTime, endDateTime);

		try {

			// =========================================================
			// FETCH MASTER DATA
			// =========================================================

			MasterData masterData = masterDataService.getMasterdatabyName(username);

			if (masterData == null) {

				log.error("❌ MasterData not found for user={}", username);

				return "MasterData not found for user : " + username;
			}

			// =========================================================
			// FETCH SESSION
			// =========================================================

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(username);

			if (session == null) {

				log.error("❌ Session not authenticated for user={}", username);

				return "Session not authenticated for user : " + username;
			}

			// =========================================================
			// CALL ALERT API
			// =========================================================

			AlertRegistration alertRegistration = getReturnFileCountAlert(masterData, startDateTime, endDateTime,
					session, strYear);

			// =========================================================
			// NULL OBJECT CHECK
			// =========================================================

			if (alertRegistration == null) {

				log.warn("⚠️ AlertRegistration object is null");

				return "No response received from Alert API";
			}

			// =========================================================
			// FAILURE RESPONSE
			// =========================================================

			if (Boolean.FALSE.equals(alertRegistration.getIsSuccess())) {

				String errorMsg = alertRegistration.getMsg();

				if (errorMsg == null || errorMsg.trim().isEmpty()) {
					errorMsg = "Unknown error received from GST API";
				}

				log.error("❌ Alert API failed | message={}", errorMsg);

				return "Alert API failed : " + errorMsg;
			}

			// =========================================================
			// EMPTY MESSAGE CHECK
			// =========================================================

			if (alertRegistration.getMsg() == null || alertRegistration.getMsg().trim().isEmpty()) {

				log.warn("⚠️ No message returned from Alert API");

				return "Alert API executed successfully but no message returned";
			}

			// =========================================================
			// SUCCESS RESPONSE
			// =========================================================

			log.info("✅ Alert API processed successfully | message={}", alertRegistration.getMsg());

			return "SUCCESS : " + alertRegistration.getMsg();

		} catch (Exception e) {

			log.error("🔥 Exception occurred while processing CRN API for user={} caseType={} error={}", username,
					strYear, e.getMessage(), e);

			return "Exception occurred while processing request : " + e.getMessage();
		}
	}

	private AlertRegistration getReturnFileCountAlert(MasterData masterData, String startDateTime, String endDateTime,
			GSTUserSession gstUserSessions, String caseType) {

		AlertRegistration returnAlertRegistrationCountCrnJson = new AlertRegistration();

		try {

			log.info("▶️ Calling Alert File Count API | Year={} | start={} | end={}", caseType, startDateTime,
					endDateTime);

			// =========================================================
			// GET API DETAILS
			// =========================================================

			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL_ALERT_lIST);

			if (apiDetails == null) {

				log.error("❌ API details not found");

				returnAlertRegistrationCountCrnJson.setIsSuccess(false);

				returnAlertRegistrationCountCrnJson.setMsg("API details not found");

				return returnAlertRegistrationCountCrnJson;
			}

			// =========================================================
			// HEADERS
			// =========================================================

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, gstUserSessions.getAuthToken(),
					apiDetails.getApiContentType());

			// =========================================================
			// REQUEST PARAMS
			// =========================================================

			Map<String, String> params = getParamsForGetReturnFileCountAlert(apiDetails, masterData, startDateTime,
					endDateTime, caseType);

			String apiPath = authenticationHelper
					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

			log.debug("📡 ALERT File Count API Path={}", apiPath);

			// =========================================================
			// DATE FORMAT FIX
			// =========================================================

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			String formattedStartDateTime = startDateTime.replaceFirst(":", " ");

			String formattedEndDateTime = endDateTime.replaceFirst(":", " ");

			String partitionDate = startDateTime.substring(0, 10);

			LocalDate partitionFyDate = LocalDate.parse(partitionDate);

			// =========================================================
			// SET COMMON FIELDS
			// =========================================================

			returnAlertRegistrationCountCrnJson.setStartTm(LocalDateTime.parse(formattedStartDateTime, formatter));

			returnAlertRegistrationCountCrnJson.setEndTm(LocalDateTime.parse(formattedEndDateTime, formatter));

			returnAlertRegistrationCountCrnJson.setPartitionFy(partitionFyDate);

			returnAlertRegistrationCountCrnJson.setCreateDateTime(Instant.now());

			returnAlertRegistrationCountCrnJson.setUpdatedDateTime(Instant.now());

			returnAlertRegistrationCountCrnJson.setYear(caseType);

			// =========================================================
			// API CALL
			// =========================================================

			GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

			// =========================================================
			// NULL RESPONSE
			// =========================================================

			if (response == null) {

				log.error("❌ Null response from API");

				returnAlertRegistrationCountCrnJson.setIsSuccess(false);

				returnAlertRegistrationCountCrnJson.setMsg("Null response received from GST API");

				AlertRegistration kk = alertRegistrationRepository.save(returnAlertRegistrationCountCrnJson);

				log.info("💾 ALERT_REGISTRATION saved | ALERT_ID={}", kk.getAlertId());

				return returnAlertRegistrationCountCrnJson;
			}

			// =========================================================
			// SUCCESS RESPONSE
			// =========================================================

			if ("1".equals(response.getStatus_cd())) {

				returnAlertRegistrationCountCrnJson.setIsSuccess(true);

				if (response.getData() != null && !response.getData().trim().isEmpty()) {

					// =====================================================
					// DECODE BASE64
					// =====================================================

					byte[] decodedData = Base64.getDecoder().decode(response.getData());

					String decodedJson = new String(decodedData, StandardCharsets.UTF_8);

					log.debug("📄 Decoded CRN JSON={}", decodedJson);

					JsonNode jsonNode = objectMapper.readTree(decodedJson);

					// =====================================================
					// SAVE JSON
					// =====================================================

					returnAlertRegistrationCountCrnJson.setJsonData(jsonNode.toString());

					// =====================================================
					// FETCH COUNT
					// =====================================================

					Long count = jsonNode.path("dayCount").asLong();

					log.info("✅ CRN Count received={}", count);

					returnAlertRegistrationCountCrnJson.setDayCount(count);

					returnAlertRegistrationCountCrnJson.setMsg("CRN Count received : " + count);

					returnAlertRegistrationCountCrnJson.setStatus(count > 0 ? "FOUND" : "NOT_FOUND");

					// =====================================================
					// SAVE ALERT_REGISTRATION
					// =====================================================

					AlertRegistration savedAlert = alertRegistrationRepository
							.save(returnAlertRegistrationCountCrnJson);

					log.info("💾 ALERT_REGISTRATION saved | ALERT_ID={}", savedAlert.getAlertId());

					// =====================================================
					// SAVE ALERT_DETAILS_REGISTRATION
					// =====================================================

					JsonNode alertsArray = jsonNode.path("alerts");

					if (alertsArray.isArray() && alertsArray.size() > 0) {

						List<AlertDetailsRegistration> detailList = new ArrayList<>();

						for (JsonNode alertNode : alertsArray) {

							AlertDetailsRegistration detail = new AlertDetailsRegistration();

							// =========================================
							// COMMON VALUES
							// =========================================

							detail.setAlertId(savedAlert.getAlertId());

							detail.setDayCount(jsonNode.path("dayCount").asInt());

							detail.setPartitionFy(savedAlert.getPartitionFy());

							// =========================================
							// JSON VALUES
							// =========================================

							detail.setAlertCd(alertNode.path("alertCd").asText(null));

							detail.setEntityId(alertNode.path("entityId").asText(null));

							detail.setEntityTyp(alertNode.path("entityTyp").asText(null));

							// =========================================
							// INSERT TIME
							// =========================================

							String insertTmStr = alertNode.path("insert_tm").asText(null);

							if (insertTmStr != null && !insertTmStr.isEmpty()) {

								detail.setInsertTm(LocalDateTime.parse(insertTmStr,
										DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
							}

							detailList.add(detail);
						}

						// =========================================
						// SAVE ALL DETAILS
						// =========================================

						alertDetailsRegistrationRepository.saveAll(detailList);

						log.info("💾 ALERT_DETAILS_REGISTRATION saved | count={}", detailList.size());
					}

				} else {

					log.warn("⚠️ Response data is empty");

					returnAlertRegistrationCountCrnJson.setMsg("API success but response data is empty");

					returnAlertRegistrationCountCrnJson.setStatus("EMPTY_DATA");

					AlertRegistration kk = alertRegistrationRepository.save(returnAlertRegistrationCountCrnJson);

					log.info("💾 ALERT_REGISTRATION saved | ALERT_ID={}", kk.getAlertId());
				}

			}

			// =========================================================
			// FAILURE RESPONSE
			// =========================================================

			else {

				returnAlertRegistrationCountCrnJson.setIsSuccess(false);

				String errorMessage = "Unknown error from GST API";

				if (response.getError() != null && response.getError().get("message") != null) {

					errorMessage = response.getError().get("message");
				}

				returnAlertRegistrationCountCrnJson.setMsg(errorMessage);

				returnAlertRegistrationCountCrnJson.setStatus("FAILED");

				log.error("❌ GST API error | {}", errorMessage);

				log.debug("Full API Response={}", response);

				AlertRegistration kk = alertRegistrationRepository.save(returnAlertRegistrationCountCrnJson);

				log.info("💾 ALERT_REGISTRATION saved | ALERT_ID={}", kk.getAlertId());
			}

		} catch (Exception ex) {

			log.error("🔥 Exception while calling CRN File Count API | start={} | caseType={} | error={}",
					startDateTime, caseType, ex.getMessage(), ex);

			returnAlertRegistrationCountCrnJson.setIsSuccess(false);

			returnAlertRegistrationCountCrnJson.setMsg(ex.getMessage());

			returnAlertRegistrationCountCrnJson.setStatus("EXCEPTION");

			returnAlertRegistrationCountCrnJson.setUpdatedDateTime(Instant.now());

			alertRegistrationRepository.save(returnAlertRegistrationCountCrnJson);
		}

		return returnAlertRegistrationCountCrnJson;
	}

	private Map<String, String> getParamsForGetReturnFileCountAlert(APIDetails apiDetails, MasterData masterData,
			String startDateTime, String endDateTime, String caseType) {

		Map<String, String> params = new HashMap<String, String>();

		params.put("action", "ALERT");

		params.put("state_cd", masterData.getStateCd());

		// REMOVE SECONDS
		params.put("start_tm", LocalDateTime.parse(startDateTime, ALERT_FORMATTER).format(ALERT_FORMATTER_ONLY));

		// REMOVE SECONDS
		params.put("end_tm", LocalDateTime.parse(endDateTime, ALERT_FORMATTER).format(ALERT_FORMATTER_ONLY));

		params.put("page_nm", "1");

		return params;
	}

	public String getAllAndSaveIntoDbViaGstin(String taxpayerType) {

		log.info("Starting GSTIN registration processing for taxpayerType: {}", taxpayerType);

		try {

			List<GstinEntity> gstinList = gstinRepository
					.findAllByIsProcessedRegistrationFalseAndTaxpayerTypeOrderById(taxpayerType);

			if (gstinList.isEmpty()) {
				log.info("No GSTIN records found for taxpayerType {}", taxpayerType);
				return "No GSTIN records found";
			}

			log.info("Total GSTIN records fetched: {}", gstinList.size());

			String resultMessage = null;

			for (GstinEntity gstinData : gstinList) {

				String gstin = gstinData.getGstin();
				log.info("Processing GSTIN {}", gstin);

				resultMessage = processGstinRegistration(gstinData, taxpayerType);

				if (resultMessage != null && resultMessage.contains("successfully")) {

					gstinData.setIsProcessedRegistration(true);
					gstinRepository.save(gstinData);

					log.info("GSTIN {} marked as processed in DB", gstin);
				}
			}

			return resultMessage;

		} catch (Exception ex) {

			log.error("Error occurred while processing GSTIN registration", ex);
			return "GSTIN processing failed";
		}
	}

	/*
	 * ========================================================= PROCESS GSTIN BASED
	 * ON TAXPAYER TYPE =========================================================
	 */

	private String processGstinRegistration(GstinEntity gstinData, String taxpayerType) {

		String gstin = gstinData.getGstin();

		switch (taxpayerType.toUpperCase()) {

		case "NORMAL":
		case "COMPOSITION":
			return fetchRegistrationData(gstin, GSTIN_IDENTITY, taxpayerType);

		case "TDS":
			return fetchRegistrationData(gstin, "UITD", taxpayerType);

		case "TCS":
			return fetchRegistrationData(gstin, "UITC", taxpayerType);

		default:
			log.warn("Invalid taxpayerType {} for GSTIN {}", taxpayerType, gstin);
			return "Invalid taxpayer type";
		}
	}

	/*
	 * ========================================================= FETCH REGISTRATION
	 * DATA =========================================================
	 */

	public String fetchRegistrationData(String gstinNum, String identityType, String taxpayerType) {

		log.info("Fetching GSTN registration data | idty: {} | GstinNum: {}", identityType, gstinNum);

		MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

		if (masterData == null) {
			log.error("MasterData not found for username {}", USERNAME);
			return "MasterData not found";
		}

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

		if (session == null) {
			log.error("Session not authenticated for user {}", USERNAME);
			return "Session not authenticated";
		}

		return callRegistrationApi(masterData, session, identityType, gstinNum, taxpayerType);
	}

	/*
	 * ========================================================= CALL GSTN API
	 * =========================================================
	 */

	private String callRegistrationApi(MasterData masterData, GSTUserSession session, String identityType,
			String gstinNum, String taxpayerType) {

		try {

			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_REGISTRATION_NORMAL_TAX_PAYER);

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, session.getAuthToken(),
					apiDetails.getApiContentType());

			Map<String, String> params = buildRequestParams(masterData, identityType, gstinNum, apiDetails);

			String uri = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
					params);

			log.info("Calling GSTN API for GstinNum {}", gstinNum);

			GSTCommonResponseBean response = restClient.get(uri, GSTCommonResponseBean.class, headers);

			if (response == null) {
				log.error("Null response received from GSTN API for GstinNum {}", gstinNum);
				return "GSTN response null";
			}

			return saveJsonFile(response, gstinNum, identityType, taxpayerType);

		} catch (Exception ex) {

			log.error("Error while calling GSTN API for GstinNum {}", gstinNum, ex);
			return "API call failed for GstinNum " + gstinNum;
		}
	}

	/*
	 * ========================================================= SAVE JSON FILE
	 * =========================================================
	 */

	private String saveJsonFile(GSTCommonResponseBean response, String gstinNum, String identityTypeq,
			String taxpayerType) {

		RegistrationDataJsonFile entity = new RegistrationDataJsonFile();
		RegistrationDataJsonFileView rDataJsonFileView = new RegistrationDataJsonFileView();

		try {

			entity.setIdentityType(taxpayerType);
			entity.setGstinNumber(gstinNum);

			entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

			rDataJsonFileView.setIdentityType(taxpayerType);
			rDataJsonFileView.setGstinNumber(gstinNum);
			rDataJsonFileView.setIsProcessed(false);
			rDataJsonFileView.setInsertDt(new Timestamp(System.currentTimeMillis()));

			// Case 1: REK is null
			if (response.getRek() == null) {

				entity.setDownloadFileStatus(false);
				rDataJsonFileView.setDownloadFileStatus(false);
				registrationDataJsonFileRepository.save(entity);
				registrationDataJsonFileViewRepository.save(rDataJsonFileView);

				log.warn("Missing REK key in response for GstinNum {}", gstinNum);
				return "GSTN: " + gstinNum + " REK missing";
			}

			// Decode JSON
			String jsonString = AESEncryption.baseDecode(response.getData());

			// Convert String → JsonNode for DB
			JsonNode jsonNode = objectMapper.readTree(jsonString);

			// Platform independent path
			Path folderPath = Paths.get(normalAndCompositionFileLocation, taxpayerType);

			// Create directory if not exists
			Files.createDirectories(folderPath);

			Path filePath = folderPath.resolve(gstinNum + ".json");

			entity.setFilePath(filePath.toString());
			rDataJsonFileView.setFilePath(filePath.toString());

			// Write file if not exists
			if (!Files.exists(filePath)) {

				Files.write(filePath, jsonString.getBytes());

				entity.setDownloadFileStatus(true);
				rDataJsonFileView.setDownloadFileStatus(true);

				log.info("File saved for Gstin {}", gstinNum);

			} else {

				entity.setDownloadFileStatus(true);
				rDataJsonFileView.setDownloadFileStatus(true);
				log.info("File already exists for Gstin {}", gstinNum);
			}

			// Save JSON in DB
			entity.setJsonData(jsonNode);
			entity.setIsSuccess(true);
			rDataJsonFileView.setIsSuccess(true);
			registrationDataJsonFileRepository.save(entity);
			registrationDataJsonFileViewRepository.save(rDataJsonFileView);

			return "GSTN: " + gstinNum + " Data saved successfully";

		} catch (Exception ex) {

			entity.setDownloadFileStatus(false);
			registrationDataJsonFileRepository.save(entity);
			registrationDataJsonFileViewRepository.save(rDataJsonFileView);

			log.error("Error saving JSON file for GstinNum {}", gstinNum, ex);

			return "File save failed for GstinNum " + gstinNum;
		}
	}

	/*
	 * ========================================================= BUILD API
	 * PARAMETERS =========================================================
	 */

	private Map<String, String> buildRequestParams(MasterData masterData, String identityType, String id,
			APIDetails apiDetails) {

		Map<String, String> params = new HashMap<>();

		params.put("action", apiDetails.getApiAction());
		params.put("state_cd", masterData.getStateCd());
		params.put("idty", identityType);
		params.put("id", id);

		return params;
	}
	//

	public String processRegistrationJson(String identityType) {

		long startTime = System.currentTimeMillis();

		ObjectMapper objectMapper = new ObjectMapper();

		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

		List<RegistrationDataJsonFileView> files = registrationDataJsonFileViewRepository
				.findAllByIdentityTypeAndIsProcessedFalseAndDownloadFileStatusTrueOrderById(identityType);

		int totalFiles = files.size();

		log.info("Total files to process for {} : {}", identityType, totalFiles);

		for (int i = 0; i < totalFiles; i += BATCH_SIZE) {

			int start = i;
			int end = Math.min(i + BATCH_SIZE, totalFiles);

			executorService.submit(() -> {

				for (int j = start; j < end; j++) {

					RegistrationDataJsonFileView fileData = files.get(j);

					String jsonFilePath = fileData.getFilePath();

					try {

						log.info("Processing file: {}", jsonFilePath);

						File file = new File(jsonFilePath);

						if (!file.exists()) {
							log.warn("File not found: {}", jsonFilePath);
							continue;
						}

						// NORMAL REGISTRATION
						if ("normal".equalsIgnoreCase(identityType)) {

							RegistrationNormalTaxPayer data = objectMapper.readValue(file,
									RegistrationNormalTaxPayer.class);

							normalRegistrationRepository.save(data);

							if (data.getGstin() != null) {
								markFileProcessed(fileData);
							}

						}

						// TDS/TCS REGISTRATION
						else if ("tds".equalsIgnoreCase(identityType) || "tcs".equalsIgnoreCase(identityType)) {

							RegistrationTdsTcs data = objectMapper.readValue(file, RegistrationTdsTcs.class);

							DataCleaner.cleanObject(data);

							tdsTcsRegistrationRepository.save(data);

							if (data.getGstin() != null) {
								markFileProcessed(fileData);
							}
						}

					} catch (UnrecognizedPropertyException e) {

						log.error("Unknown field in JSON file {} : {}", jsonFilePath, e.getMessage());

					} catch (IOException e) {

						log.error("Failed to read file {} : {}", jsonFilePath, e.getMessage());

					} catch (OutOfMemoryError e) {

						log.error("OutOfMemoryError while processing {} : {}", jsonFilePath, e.getMessage());
						Runtime.getRuntime().gc();

					} catch (Exception e) {

						log.error("Unexpected error while processing {} ", jsonFilePath, e);
					}
				}
			});
		}

		shutdownExecutor(executorService);

		long endTime = System.currentTimeMillis();

		log.info("Processing completed for {}. Total files: {}. Time taken: {} seconds", identityType, totalFiles,
				(endTime - startTime) / 1000);

		return "Data processing completed for " + identityType;
	}

	private void markFileProcessed(RegistrationDataJsonFileView fileData) {

		fileData.setIsProcessed(true);
		registrationDataJsonFileViewRepository.save(fileData);

		log.info("File marked as processed for GSTIN: {}", fileData.getGstinNumber());
	}

	private void shutdownExecutor(ExecutorService executorService) {

		executorService.shutdown();

		try {

			if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {

				log.warn("Executor did not terminate in the specified time.");

				executorService.shutdownNow();
			}

		} catch (InterruptedException ex) {

			log.error("Executor interrupted", ex);

			executorService.shutdownNow();

			Thread.currentThread().interrupt();
		}
	}

	public List<String> getDownloadDocumentViaGstin(String userName) {

		List<String> downloadResponses = new ArrayList<>();

		List<RegDocuments> documentList = regDocumentsRepository.findAll();

		if (documentList.isEmpty()) {
			log.info("No records found in reg_documents table");
			return Collections.singletonList("No records found.");
		}

		for (RegDocuments doc : documentList) {

			try {

				String response = registrationServiceImpl.getDownloadDocumentViaGstin(doc, userName);

				downloadResponses.add(response);

			} catch (Exception e) {

				log.error("Error processing documentId={} gstin={}", doc.getDocumentId(), doc.getGstin(), e);

				downloadResponses.add("Failed for documentId : " + doc.getDocumentId());
			}
		}

		return downloadResponses;
	}

	public String processAllDocuments(String userName) {

		long startTime = System.currentTimeMillis();
		log.info("Started bulk document processing for user={}", userName);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);
		if (masterData == null)
			return "Master data not found";

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);
		if (session == null)
			return "Session not authenticated";

		ExecutorService executor = Executors.newFixedThreadPool(50);

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failedCount = new AtomicInteger(0);

		// 🔥 Blocking Queue
		BlockingQueue<RegDocuments> updateQueue = new LinkedBlockingQueue<>();

		final int BATCH_SIZE = 500;

		// 🔥 DB SAVER THREAD
		Thread dbSaverThread = new Thread(() -> {

			List<RegDocuments> batch = new ArrayList<>();

			try {
				while (true) {

					RegDocuments doc = updateQueue.poll(5, TimeUnit.SECONDS);

					if (doc != null) {
						batch.add(doc);
					}

					// SAVE WHEN BATCH FULL
					if (batch.size() >= BATCH_SIZE) {
						regDocumentsRepository.saveAll(batch);
						regDocumentsRepository.flush();

						log.info("✅ Batch saved size={}", batch.size());
						batch.clear();
					}

					// Stop condition
					if (Thread.currentThread().isInterrupted()) {
						break;
					}
				}

				// FINAL SAVE
				if (!batch.isEmpty()) {
					regDocumentsRepository.saveAll(batch);
					regDocumentsRepository.flush();
					log.info("✅ Final batch saved size={}", batch.size());
				}

			} catch (Exception e) {
				log.error("DB Saver Thread failed", e);
			}

		});

		dbSaverThread.start();

		try {

			while (true) {

				Pageable pageable = PageRequest.of(0, 500);

				Page<RegDocuments> page = regDocumentsRepository.findByIsSuccessIsNullOrIsSuccessFalse(pageable);

				if (page.isEmpty()) {
					log.info("✅ No more pending records found. Exiting loop.");
					break;
				}

				log.info("Fetched records size={}", page.getContent().size());

				List<CompletableFuture<Void>> futures = new ArrayList<>();

				for (RegDocuments doc : page.getContent()) {

					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

						try {
							registrationServiceImpl.processSingleDocumentLatest(masterData, session, doc, updateQueue);
							successCount.incrementAndGet();

						} catch (Exception e) {
							failedCount.incrementAndGet();
							log.error("Failed documentId={} gstin={}", doc.getDocumentId(), doc.getGstin(), e);

							registrationServiceImpl.updateErrorRecord(doc, e.getMessage(), updateQueue);
						}

					}, executor);

					futures.add(future);
				}

				// wait for all threads
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

				log.info("Loop completed processed={}", page.getContent().size());
			}

		} catch (Exception e) {
			log.error("Bulk processing failed", e);
		} finally {

			// stop executor
			executor.shutdown();
			try {
				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			} catch (InterruptedException e) {
				executor.shutdownNow();
				Thread.currentThread().interrupt();
			}

			// 🔥 STOP DB THREAD
			dbSaverThread.interrupt();
			try {
				dbSaverThread.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		long totalTime = System.currentTimeMillis() - startTime;

		log.info("Completed | success={} | failed={} | time={} ms", successCount.get(), failedCount.get(), totalTime);

		return "Success: " + successCount.get() + ", Failed: " + failedCount.get() + ", Time(ms): " + totalTime;
	}

	public String processFewDocuments(String userName) {

		long startTime = System.currentTimeMillis();
		log.info("Started bulk document processing for user={}", userName);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);
		if (masterData == null)
			return "Master data not found";

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);
		if (session == null)
			return "Session not authenticated";

		ExecutorService executor = Executors.newFixedThreadPool(50);

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failedCount = new AtomicInteger(0);

		// 🔥 Blocking Queue
		BlockingQueue<RentActivePpbzdtls> updateQueue = new LinkedBlockingQueue<>();

		final int BATCH_SIZE = 1;

		// 🔥 DB SAVER THREAD
		Thread dbSaverThread = new Thread(() -> {

			List<RentActivePpbzdtls> batch = new ArrayList<>();

			try {
				while (true) {

					RentActivePpbzdtls doc = updateQueue.poll(5, TimeUnit.SECONDS);

					if (doc != null) {
						batch.add(doc);
					}

					// SAVE WHEN BATCH FULL
					if (batch.size() >= BATCH_SIZE) {
						RentActivePpbzdtlsRepository.saveAll(batch);
						RentActivePpbzdtlsRepository.flush();

						log.info("✅ Batch saved size={}", batch.size());
						batch.clear();
					}

					// Stop condition
					if (Thread.currentThread().isInterrupted()) {
						break;
					}
				}

				// FINAL SAVE
				if (!batch.isEmpty()) {
					RentActivePpbzdtlsRepository.saveAll(batch);
					RentActivePpbzdtlsRepository.flush();
					log.info("✅ Final batch saved size={}", batch.size());
				}

			} catch (Exception e) {
				log.error("DB Saver Thread failed", e);
			}

		});

		dbSaverThread.start();

		try {

			while (true) {

				Pageable pageable = PageRequest.of(0, 500);

				Page<RentActivePpbzdtls> page = RentActivePpbzdtlsRepository
						.findByIsProcessedNullOrIsProcessedFalse(pageable);

				if (page.isEmpty()) {
					log.info("✅ No more pending records found. Exiting loop.");
					break;
				}

				log.info("Fetched records size={}", page.getContent().size());

				List<CompletableFuture<Void>> futures = new ArrayList<>();

				for (RentActivePpbzdtls doc : page.getContent()) {

					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

						try {
							registrationServiceImpl.processSingleDocumentFew(masterData, session, doc, updateQueue);
							successCount.incrementAndGet();

						} catch (Exception e) {
							failedCount.incrementAndGet();
							log.error("Failed documentId={} gstin={}", doc.getDocumentId(), doc.getGstin(), e);

							registrationServiceImpl.updateErrorRecordFew(doc, e.getMessage(), updateQueue);
						}

					}, executor);

					futures.add(future);
				}

				// wait for all threads
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

				log.info("Loop completed processed={}", page.getContent().size());
			}

		} catch (Exception e) {
			log.error("Bulk processing failed", e);
		} finally {

			// stop executor
			executor.shutdown();
			try {
				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			} catch (InterruptedException e) {
				executor.shutdownNow();
				Thread.currentThread().interrupt();
			}

			// 🔥 STOP DB THREAD
			dbSaverThread.interrupt();
			try {
				dbSaverThread.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		long totalTime = System.currentTimeMillis() - startTime;

		log.info("Completed | success={} | failed={} | time={} ms", successCount.get(), failedCount.get(), totalTime);

		return "Success: " + successCount.get() + ", Failed: " + failedCount.get() + ", Time(ms): " + totalTime;
	}

	public String processDateAccordingToStartAndEndMonth(int year, int startMonth, int endMonth)
			throws UnsupportedEncodingException {

		log.info("▶️ [ALERT PROCESS START] year={}, startMonth={}, endMonth={}", year, startMonth, endMonth);

		String lastResponse = null;

		LocalDateTime currentStartDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0, 0);

		LocalDateTime finalEndDateTime = LocalDateTime.of(year, endMonth, YearMonth.of(year, endMonth).lengthOfMonth(),
				23, 59, 59);

		long processStartTime = System.currentTimeMillis();

		while (!currentStartDateTime.isAfter(finalEndDateTime)) {

			// =====================================================
			// CREATE 1 HOUR WINDOW
			// =====================================================

			LocalDateTime currentEndDateTime = currentStartDateTime.plusHours(1).minusSeconds(1);

			// =====================================================
			// FORMAT DATE TIME
			// =====================================================

			String formattedStartDateTime = currentStartDateTime.format(ALERT_FORMATTER);

			String formattedEndDateTime = currentEndDateTime.format(ALERT_FORMATTER);

			try {

				String strYear = String.valueOf(year);

				// =====================================================
				// DUPLICATE CHECK
				// =====================================================

				boolean alreadyProcessed = alertRegistrationRepository
						.findByStartTmAndYear(currentStartDateTime, strYear).isPresent();

				if (alreadyProcessed) {

					log.info("⏭ Already processed | startTime={} | year={}", formattedStartDateTime, strYear);

					currentStartDateTime = currentStartDateTime.plusHours(1);

					continue;
				}

				// =====================================================
				// API CALL
				// =====================================================

				log.info("📡 Calling ALERT API | start={} | end={} | year={}", formattedStartDateTime,
						formattedEndDateTime, strYear);

				lastResponse = getAlertListByStartAndEndTime(USERNAME, formattedStartDateTime, formattedEndDateTime,
						strYear);

				// =====================================================
				// STATUS
				// =====================================================

				String status = (lastResponse != null && lastResponse.contains("SUCCESS")) ? "FOUND" : "NOT_FOUND";

				log.info("💾 ALERT saved successfully | start={} | status={}", formattedStartDateTime, status);

			} catch (Exception ex) {

				log.error("❌ Error processing ALERT | startTime={}", formattedStartDateTime, ex);
			}

			// =====================================================
			// MOVE TO NEXT HOUR
			// =====================================================

			currentStartDateTime = currentStartDateTime.plusHours(1);
		}

		long totalTime = System.currentTimeMillis() - processStartTime;

		log.info("🏁 [ALERT PROCESS END] year={} | totalTime={} ms", year, totalTime);

		return lastResponse;
	}

	// =========================================================
	// COMPLETE FLOW
	// =========================================================

	public String executeCompleteAutomation() {

		long overallStartTime = System.currentTimeMillis();

		StringBuilder finalResponse = new StringBuilder();

		// =========================================================
		// STEP 1 : ALERT API
		// =========================================================

		try {

			log.info("=====================================================");
			log.info("▶️ STEP-1 ALERT API PROCESS START");
			log.info("=====================================================");

			long step1Start = System.currentTimeMillis();

			String alertResponse = processAlertAutomatically();

			long step1Time = System.currentTimeMillis() - step1Start;

			log.info("✅ STEP-1 ALERT API PROCESS COMPLETED");
			log.info("⏱️ STEP-1 TIME={} ms", step1Time);

			finalResponse.append("\nSTEP-1 ALERT RESPONSE : ").append(alertResponse);

		} catch (Exception ex) {

			log.error("❌ STEP-1 ALERT API FAILED", ex);

			finalResponse.append("\nSTEP-1 FAILED : ").append(ex.getMessage());
		}

		// =========================================================
		// STEP 2 : ARN HANDLER
		// =========================================================

		try {

			log.info("=====================================================");
			log.info("▶️ STEP-2 ARN HANDLER PROCESS START");
			log.info("=====================================================");

			long step2Start = System.currentTimeMillis();

			String arnResponse = arnHandlerForRegistration.processJson();

			long step2Time = System.currentTimeMillis() - step2Start;

			log.info("✅ STEP-2 ARN HANDLER COMPLETED");
			log.info("⏱️ STEP-2 TIME={} ms", step2Time);

			finalResponse.append("\nSTEP-2 ARN RESPONSE : ").append(arnResponse);

		} catch (Exception ex) {

			log.error("❌ STEP-2 ARN HANDLER FAILED", ex);

			finalResponse.append("\nSTEP-2 FAILED : ").append(ex.getMessage());
		}

		// =========================================================
		// STEP 3 : REGISTRATION PROCESS
		// =========================================================

		try {

			log.info("=====================================================");
			log.info("▶️ STEP-3 REGISTRATION PROCESS START");
			log.info("=====================================================");

			long step3Start = System.currentTimeMillis();

			String registrationResponse = arnHandlerForRegistration.processRegistration();

			long step3Time = System.currentTimeMillis() - step3Start;

			log.info("✅ STEP-3 REGISTRATION PROCESS COMPLETED");
			log.info("⏱️ STEP-3 TIME={} ms", step3Time);

			finalResponse.append("\nSTEP-3 REGISTRATION RESPONSE : ").append(registrationResponse);

		} catch (Exception ex) {

			log.error("❌ STEP-3 REGISTRATION PROCESS FAILED", ex);

			finalResponse.append("\nSTEP-3 FAILED : ").append(ex.getMessage());
		}

		// =========================================================
		// FINAL SUMMARY
		// =========================================================

		long overallTime = System.currentTimeMillis() - overallStartTime;

		log.info("=====================================================");
		log.info("🏁 COMPLETE AUTOMATION FINISHED");
		log.info("⏱️ OVERALL TIME={} ms", overallTime);
		log.info("=====================================================");

		finalResponse.append("\nOVERALL TIME : ").append(overallTime).append(" ms");

		return finalResponse.toString();
	}

//	// GET-COMPARISION-REPORT
//	public String getComparisonReport(String userName) {
//
//		long startTime = System.currentTimeMillis();
//		log.info("Started bulk getComparisonReport={}", userName);
//
//		MasterData masterData = masterDataService.getMasterdatabyName(userName);
//		if (masterData == null)
//			return "Master data not found";
//
//		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);
//		if (session == null)
//			return "Session not authenticated";
//
//		ExecutorService executor = Executors.newFixedThreadPool(50);
//
//		AtomicInteger successCount = new AtomicInteger(0);
//		AtomicInteger failedCount = new AtomicInteger(0);
//
//		// 🔥 Blocking Queue
//		BlockingQueue<ReturnComparisonReportGstin> updateQueue = new LinkedBlockingQueue<>();
//
//		final int BATCH_SIZE = 1;
//
//		// 🔥 DB SAVER THREAD
//		Thread dbSaverThread = new Thread(() -> {
//
//			List<ReturnComparisonReportGstin> batch = new ArrayList<>();
//
//			try {
//				while (true) {
//
//					ReturnComparisonReportGstin doc = updateQueue.poll(5, TimeUnit.SECONDS);
//
//					if (doc != null) {
//						batch.add(doc);
//					}
//
//					// SAVE WHEN BATCH FULL
//					if (batch.size() >= BATCH_SIZE) {
//						returnComparisonReportGstinRepository.saveAll(batch);
//						returnComparisonReportGstinRepository.flush();
//
//						log.info("✅ Batch saved size={}", batch.size());
//						batch.clear();
//					}
//
//					// Stop condition
//					if (Thread.currentThread().isInterrupted()) {
//						break;
//					}
//				}
//
//				// FINAL SAVE
//				if (!batch.isEmpty()) {
//					returnComparisonReportGstinRepository.saveAll(batch);
//					returnComparisonReportGstinRepository.flush();
//					log.info("✅ Final batch saved size={}", batch.size());
//				}
//
//			} catch (Exception e) {
//				log.error("DB Saver Thread failed", e);
//			}
//
//		});
//
//		dbSaverThread.start();
//
//		try {
//
//			while (true) {
//
//				Pageable pageable = PageRequest.of(0, 5000);
//
////				Page<ReturnComparisonReportGstin> page = returnComparisonReportGstinRepository
////						.findByIsProcessedNullOrIsProcessedFalse(pageable);
//
//				Page<ReturnComparisonReportGstin> page = returnComparisonReportGstinRepository
//						.findByIsProcessedNullOrIsProcessedFalseAndCounterAttemptLessThan(10, pageable);
//
//				if (page.isEmpty()) {
//					log.info("✅ No more pending records found. Exiting loop.");
//					break;
//				}
//
//				log.info("Fetched records size={}", page.getContent().size());
//
//				List<CompletableFuture<Void>> futures = new ArrayList<>();
//
//				for (ReturnComparisonReportGstin doc : page.getContent()) {
//
//					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//
//						try {
//							registrationServiceImpl.processSingleSingleGstin(masterData, session, doc, updateQueue);
//							successCount.incrementAndGet();
//
//						} catch (Exception e) {
//							failedCount.incrementAndGet();
//							log.error("Failed Year={} gstin={}", doc.getFy(), doc.getGstin(), e);
//
//							registrationServiceImpl.updateErrorRecordGetComparisonReport(doc, updateQueue);
//						}
//
//					}, executor);
//
//					futures.add(future);
//				}
//
//				// wait for all threads
//				CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//
//				log.info("Loop completed processed={}", page.getContent().size());
//			}
//
//		} catch (Exception e) {
//			log.error("Bulk processing failed", e);
//		} finally {
//
//			// stop executor
//			executor.shutdown();
//			try {
//				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
//					executor.shutdownNow();
//				}
//			} catch (InterruptedException e) {
//				executor.shutdownNow();
//				Thread.currentThread().interrupt();
//			}
//
//			// 🔥 STOP DB THREAD
//			dbSaverThread.interrupt();
//			try {
//				dbSaverThread.join();
//			} catch (InterruptedException e) {
//				Thread.currentThread().interrupt();
//			}
//		}
//
//		long totalTime = System.currentTimeMillis() - startTime;
//
//		log.info("Completed | success={} | failed={} | time={} ms", successCount.get(), failedCount.get(), totalTime);
//
//		return "Success: " + successCount.get() + ", Failed: " + failedCount.get() + ", Time(ms): " + totalTime;
//	}

	public String processDocumentsDh(String userName) {

		long startTime = System.currentTimeMillis();
		log.info("Started bulk document processing-dh for user={}", userName);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);
		if (masterData == null)
			return "Master data not found";

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);
		if (session == null)
			return "Session not authenticated";

		ExecutorService executor = Executors.newFixedThreadPool(50);

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failedCount = new AtomicInteger(0);

		// 🔥 Blocking Queue
		BlockingQueue<DcupdtlsGstr9c> updateQueue = new LinkedBlockingQueue<>();

		final int BATCH_SIZE = 1;

		// 🔥 DB SAVER THREAD
		Thread dbSaverThread = new Thread(() -> {

			List<DcupdtlsGstr9c> batch = new ArrayList<>();

			try {
				while (true) {

					DcupdtlsGstr9c doc = updateQueue.poll(5, TimeUnit.SECONDS);

					if (doc != null) {
						batch.add(doc);
					}

					// SAVE WHEN BATCH FULL
					if (batch.size() >= BATCH_SIZE) {
						dcupdtlsGstr9cRepository.saveAll(batch);
						dcupdtlsGstr9cRepository.flush();

						log.info("✅ Batch saved size={}", batch.size());
						batch.clear();
					}

					// Stop condition
					if (Thread.currentThread().isInterrupted()) {
						break;
					}
				}

				// FINAL SAVE
				if (!batch.isEmpty()) {
					dcupdtlsGstr9cRepository.saveAll(batch);
					dcupdtlsGstr9cRepository.flush();
					log.info("✅ Final batch saved size={}", batch.size());
				}

			} catch (Exception e) {
				log.error("DB Saver Thread failed", e);
			}

		});

		dbSaverThread.start();

		try {

			while (true) {

				Pageable pageable = PageRequest.of(0, 500);

				Page<DcupdtlsGstr9c> page = dcupdtlsGstr9cRepository.findByIsProcessedNullOrIsProcessedFalseAndCounterAttemptLessThan(4,pageable);

				if (page.isEmpty()) {
					log.info("✅ No more pending records found. Exiting loop.");
					break;
				}

				log.info("Fetched records size={}", page.getContent().size());

				List<CompletableFuture<Void>> futures = new ArrayList<>();

				for (DcupdtlsGstr9c doc : page.getContent()) {

					CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

						try {
							processSingleDocumentDh(masterData, session, doc, updateQueue);
							successCount.incrementAndGet();

						} catch (Exception e) {
							failedCount.incrementAndGet();
							log.error("Failed documentId={} gstin={}", doc.getDocId(), doc.getGstin(), e);

							updateErrorRecordDw(doc, e.getMessage(), updateQueue);
						}

					}, executor);

					futures.add(future);
				}

				// wait for all threads
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

				log.info("Loop completed processed={}", page.getContent().size());
			}

		} catch (Exception e) {
			log.error("Bulk processing failed", e);
		} finally {

			// stop executor
			executor.shutdown();
			try {
				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			} catch (InterruptedException e) {
				executor.shutdownNow();
				Thread.currentThread().interrupt();
			}

			// 🔥 STOP DB THREAD
			dbSaverThread.interrupt();
			try {
				dbSaverThread.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		long totalTime = System.currentTimeMillis() - startTime;

		log.info("Completed | success={} | failed={} | time={} ms", successCount.get(), failedCount.get(), totalTime);

		return "Success: " + successCount.get() + ", Failed: " + failedCount.get() + ", Time(ms): " + totalTime;
	}

	public void processSingleDocumentDh(MasterData masterData, GSTUserSession session, DcupdtlsGstr9c doc,
			BlockingQueue<DcupdtlsGstr9c> updateQueue) {

		try {

			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_REGISTRATION_DOWNLOAD_DOCUMENT);

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, session.getAuthToken(),
					apiDetails.getApiContentType());

			Map<String, String> params = getParamsForRequestDownloadDataEntity(masterData, doc.getDocId(), apiDetails);

			String apiPath = authenticationHelper
					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

			GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

			if (response == null || response.getData() == null) {
				updateErrorRecordDw(doc, "Null response from API", updateQueue);
				return;
			}

			byte[] fileBytes = Base64.getDecoder().decode(response.getData());

			String fileNameWithoutExt = buildFileNamedW(doc);

//			if (extension == null) {
//				updateErrorRecordDw(doc, "Unsupported file type", updateQueue);
//				return;
//			}

			// String fileName = fileNameWithoutExt + extension;
			String fileName = doc.getGstin() + "_" + doc.getDocId() + "_" + doc.getDocNam();

			// String remoteDir = BASE_PATH_DW + "/" + doc.getId();

			String remoteDir = BASE_PATH_DW + "/" + doc.getFp();

			String remotePath = SftpUtil.uploadFileToSftp(fileBytes, remoteDir, fileName);

			updateSuccessRecordDw(doc, remotePath, fileNameWithoutExt, updateQueue);

		} catch (Exception e) {
			updateErrorRecordDw(doc, e.getMessage(), updateQueue);
		}
	}

	public void updateErrorRecordDw(DcupdtlsGstr9c doc, String errorMessage, Queue<DcupdtlsGstr9c> queue) {

		try {

			doc.setIsProcessed(false);
			doc.setInsertDt(new java.sql.Date(System.currentTimeMillis()));
			doc.setCounterAttempt(doc.getCounterAttempt()+1);

			queue.add(doc);

		} catch (Exception e) {
			log.error("Error updating record documentId={}", doc.getDocId(), e);
		}
	}

	public void updateSuccessRecordDw(DcupdtlsGstr9c doc, String path, String fileName, Queue<DcupdtlsGstr9c> queue) {

		try {
			doc.setPath(path);
			doc.setFileName(fileName);
			doc.setIsProcessed(true);
			//doc.setCounterAttempt(0);
			doc.setInsertDt(new java.sql.Date(System.currentTimeMillis()));

			queue.add(doc);

		} catch (Exception e) {
			log.error("Error updating record documentId={}", doc.getDocId(), e);
		}
	}

	private String buildFileNamedW(DcupdtlsGstr9c doc) {

		return doc.getGstin() + "_" + doc.getDocType() + "_" + doc.getFp() + "_" + doc.getDocId();
	}

	private Map<String, String> getParamsForRequestDownloadDataEntity(MasterData masterData, String documentId,
			APIDetails apiDetailsForFileCount) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", apiDetailsForFileCount.getApiAction());
		params.put("state_cd", masterData.getStateCd());
		params.put("docid", documentId);
		return params;

	}

	// for the exception
	public String processAlertByDateRange(LocalDateTime currentStartDateTime, LocalDateTime finalEndDateTime)
			throws UnsupportedEncodingException {

		log.info("▶️ [ALERT PROCESS START]");
		log.info("Start DateTime : {}", currentStartDateTime);
		log.info("End DateTime   : {}", finalEndDateTime);

		String lastResponse = null;

		long processStartTime = System.currentTimeMillis();

		while (!currentStartDateTime.isAfter(finalEndDateTime)) {

			LocalDateTime currentEndDateTime = currentStartDateTime.plusHours(1);

			if (currentEndDateTime.isAfter(finalEndDateTime)) {
				currentEndDateTime = finalEndDateTime;
			}

			String formattedStartDateTime = currentStartDateTime.format(ALERT_FORMATTER);

			String formattedEndDateTime = currentEndDateTime.format(ALERT_FORMATTER);

			try {

				String year = String.valueOf(currentStartDateTime.getYear());

				log.info("📡 Calling ALERT API | start={} | end={}", formattedStartDateTime, formattedEndDateTime);

				lastResponse = getAlertListByStartAndEndTime(USERNAME, formattedStartDateTime, formattedEndDateTime,
						year);

				String status = (lastResponse != null && lastResponse.contains("SUCCESS")) ? "FOUND" : "NOT_FOUND";

				log.info("💾 ALERT processed successfully | start={} | status={}", formattedStartDateTime, status);

			} catch (Exception ex) {

				log.error("❌ Error processing ALERT | startTime={}", formattedStartDateTime, ex);
			}

			currentStartDateTime = currentEndDateTime;
		}

		long totalTime = System.currentTimeMillis() - processStartTime;

		log.info("🏁 [ALERT PROCESS END] totalTime={} ms", totalTime);

		return lastResponse;
	}

	public String retryAlertException() {

		log.info("▶️ [RETRY ALERT EXCEPTION START]");

		List<AlertRegistration> exceptionRecords = alertRegistrationRepository.getExceptionAlerts();

		if (exceptionRecords == null || exceptionRecords.isEmpty()) {

			log.info("No exception records found");

			return "No exception records found";
		}

		int successCount = 0;
		int failedCount = 0;

		for (AlertRegistration alert : exceptionRecords) {

			try {

				boolean processed = retrySingleAlertException(alert);

				if (processed) {
					successCount++;
				} else {
					failedCount++;
				}

			} catch (Exception ex) {

				failedCount++;

				log.error("Error processing alertId={}", alert.getAlertId(), ex);
			}
		}

		log.info("🏁 [RETRY ALERT EXCEPTION END] Success={} Failed={}", successCount, failedCount);

		return "Completed. Success=" + successCount + ", Failed=" + failedCount;
	}

	private boolean retrySingleAlertException(AlertRegistration existingAlert) {

		try {

			log.info("Processing alertId={}", existingAlert.getAlertId());

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {

				log.error("MasterData not found");

				return false;
			}

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {

				log.error("Session not found");

				return false;
			}

			String startDateTime = existingAlert.getStartTm().format(ALERT_FORMATTER);

			String endDateTime = existingAlert.getEndTm().format(ALERT_FORMATTER);

			String year = existingAlert.getYear();

			return retryAlertApiCall(existingAlert, masterData, startDateTime, endDateTime, session, year);

		} catch (Exception ex) {

			log.error("Exception while retrying alertId={}", existingAlert.getAlertId(), ex);

			return false;
		}
	}

	private boolean retryAlertApiCall(AlertRegistration existingAlert, MasterData masterData, String startDateTime,
			String endDateTime, GSTUserSession gstUserSessions, String caseType) {

		try {

			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL_ALERT_lIST);

			if (apiDetails == null) {

				log.error("API Details not found");

				return false;
			}

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, gstUserSessions.getAuthToken(),
					apiDetails.getApiContentType());

			Map<String, String> params = getParamsForGetReturnFileCountAlert(apiDetails, masterData, startDateTime,
					endDateTime, caseType);

			String apiPath = authenticationHelper
					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

			GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

			if (response == null) {

				log.error("Null response");

				return false;
			}

			if (!"1".equals(response.getStatus_cd())) {

				log.error("API failed");

				return false;
			}

			if (response.getData() == null || response.getData().trim().isEmpty()) {

				log.warn("Empty response data");

				return false;
			}

			byte[] decodedData = Base64.getDecoder().decode(response.getData());

			String decodedJson = new String(decodedData, StandardCharsets.UTF_8);

			JsonNode jsonNode = objectMapper.readTree(decodedJson);

			Long count = jsonNode.path("dayCount").asLong();

			// =====================================
			// UPDATE EXISTING RECORD
			// =====================================

			existingAlert.setIsSuccess(true);

			existingAlert.setStatus(count > 0 ? "FOUND" : "NOT_FOUND");

			existingAlert.setDayCount(count);

			existingAlert.setJsonData(jsonNode.toString());

			existingAlert.setMsg("CRN Count received : " + count);

			existingAlert.setUpdatedDateTime(Instant.now());

			AlertRegistration updatedAlert = alertRegistrationRepository.save(existingAlert);

			log.info("Updated alertId={}", updatedAlert.getAlertId());

			// =====================================
			// SAVE DETAILS
			// =====================================

			JsonNode alertsArray = jsonNode.path("alerts");

			if (alertsArray.isArray() && alertsArray.size() > 0) {

				List<AlertDetailsRegistration> detailList = new ArrayList<>();

				for (JsonNode alertNode : alertsArray) {

					AlertDetailsRegistration detail = new AlertDetailsRegistration();

					detail.setAlertId(updatedAlert.getAlertId());

					detail.setDayCount(jsonNode.path("dayCount").asInt());

					detail.setPartitionFy(updatedAlert.getPartitionFy());

					detail.setAlertCd(alertNode.path("alertCd").asText(null));

					detail.setEntityId(alertNode.path("entityId").asText(null));

					detail.setEntityTyp(alertNode.path("entityTyp").asText(null));

					String insertTm = alertNode.path("insert_tm").asText(null);

					if (insertTm != null && !insertTm.isEmpty()) {

						detail.setInsertTm(
								LocalDateTime.parse(insertTm, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					}

					detailList.add(detail);
				}

				alertDetailsRegistrationRepository.saveAll(detailList);

				log.info("Saved detail count={}", detailList.size());
			}

			return true;

		} catch (Exception ex) {

			log.error("Retry failed for alertId={}", existingAlert.getAlertId(), ex);

			return false;
		}
	}

}