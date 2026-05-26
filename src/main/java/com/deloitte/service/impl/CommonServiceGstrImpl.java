package com.deloitte.service.impl;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.bean.Result;
import com.deloitte.common.bean.ReturnFileCountResponseBean;
import com.deloitte.common.bean.ReturnFileDetailsResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.filecounter.DateCountData;
import com.deloitte.returns.entity.filecounter.ReturnFileCountResponse;
import com.deloitte.returns.entity.filecounter.ReturnFileDetailResponse;
import com.deloitte.returns.entity.log.BaseJsonEntity;
import com.deloitte.returns.entity.recon.Recon;
import com.deloitte.service.abs.CommonServiceImplAbs;
import com.deloitte.service.support.AESEncryption;
import com.deloitte.service.support.CommonControllerGstrUtilityImpl;
import com.deloitte.service.utility.GstUtil;
import com.deloitte.service.utility.SftpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommonServiceGstrImpl extends CommonServiceImplAbs {

	private final CommonControllerGstrUtilityImpl commonControllerGstrUtilityImpl;

	CommonServiceGstrImpl(CommonControllerGstrUtilityImpl commonControllerGstrUtilityImpl) {
		this.commonControllerGstrUtilityImpl = commonControllerGstrUtilityImpl;
	}

	// 1
	public String getCommonGstrAndOtherDownloadAsm(String username, String date, String application, String info) {

		log.info("▶️ [START] getCommonGstrAndOtherDownloadAsm | username={}, date={}, application={}, info={}",
				username, date, application, info);

		long startTime = System.currentTimeMillis();

		try {
			// Step 1: Fetch Master Data

			MasterData masterData = masterDataService.getMasterdatabyName(username);// 1:1
			if (masterData == null) {
				log.error("❌ MasterData not found for username={}", username);
				throw new IllegalStateException("User not found in Master Data table");
			}

			// Step 2: Fetch User Session
			GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(username);// 1:2
			if (gstUserSession == null) {
				log.error("❌ Session not authenticated for username={}", username);
				throw new IllegalStateException("Session not authenticated. Please authenticate first.");
			}

			// Step 3: Get File Count
			ReturnFileCountResponseBean fileCountResponse = getReturnFileCount(masterData, date, gstUserSession,
					application, info);// 1:3

			if (fileCountResponse == null || fileCountResponse.getNum_files() == null) {
				log.warn("⚠ No files available | username={}, date={}, application={}, info={}", username, date,
						application, info);
				return "No files available for download.";
			}

			log.info("📊 Total files available: {}", fileCountResponse.getNum_files());

			// Step 4: Fetch File Details & Download
			String response = getFileDetails(fileCountResponse, masterData, date, gstUserSession, application, info,
					fileCountResponse.getReturnFileCountId());// 1:4

			long timeTaken = System.currentTimeMillis() - startTime;

			log.info("✅ [SUCCESS] Download completed | username={} | Files={} | Time={} ms", username,
					fileCountResponse.getNum_files(), timeTaken);

			return response;

		} catch (Exception ex) {
			log.error("🔥 System error occurred while processing download", ex);
			throw new RuntimeException("Download process failed", ex);
		}
	}

	// 1:3
	private ReturnFileCountResponseBean getReturnFileCount(MasterData masterData, String date,
			GSTUserSession gstUserSession, String application, String info) {

		final long startTime = System.currentTimeMillis();

		final String username = masterData.getUserName();
		final String normalizedApplication = commonServiceGstrImplSupport.normalizeApplication(application);

		log.info("START :: getReturnFileCount | application={} | date={} | username={}", normalizedApplication, date,
				username);

		ReturnFileCountResponseBean responseBean = new ReturnFileCountResponseBean();

		ReturnFileCountResponse entity = null;

		try {

			/*
			 * STEP-1 : DATE CONVERSION
			 */
			LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			Date dbDate = Date.valueOf(parsedDate);

			log.debug("Date parsed successfully | inputDate={} | dbDate={}", date, dbDate);

			/*
			 * STEP-2 : FETCH EXISTING RECORD
			 */
			entity = returnFileCountRepository.findByDtAndTy(dbDate, normalizedApplication)
					.orElseGet(ReturnFileCountResponse::new);

			boolean isExistingRecord = entity.getReturnFileCountId() != null;

			log.info("Database record {} | application={} | date={}", isExistingRecord ? "FOUND" : "NOT_FOUND",
					normalizedApplication, dbDate);

			/*
			 * STEP-3 : SET COMMON ENTITY VALUES
			 */
			entity.setDt(dbDate);
			entity.setTy(normalizedApplication);
			entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

			/*
			 * STEP-4 : RESOLVE API DETAILS
			 */
			APIDetails apiDetails = commonServiceGstrImplSupport.resolveApiDetails(application);

			log.debug("API details resolved | apiName={} | contentType={}", apiDetails.getApiName(),
					apiDetails.getApiContentType());

			/*
			 * STEP-5 : PREPARE HEADERS
			 */
			HttpHeaders headers = commonServiceGstrImplSupport.getDefaultHeaders(masterData,
					gstUserSession.getAuthToken(), apiDetails.getApiContentType());

			log.debug("Headers prepared successfully | username={}", username);

			/*
			 * STEP-6 : PREPARE REQUEST URL
			 */
			Map<String, String> params = commonServiceGstrImplSupport.getParamsForGetReturnFileCount(apiDetails,
					masterData, date, application);

			String fullPath = authenticationHelper
					.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

			entity.setUrl(fullPath);

			log.info("Calling GST API | application={} | date={} | url={}", normalizedApplication, date, fullPath);

			/*
			 * STEP-7 : GST API CALL
			 */
			GSTCommonResponseBean gstResponse = restClient.get(fullPath, GSTCommonResponseBean.class, headers);

			/*
			 * STEP-8 : HANDLE SUCCESS RESPONSE
			 */
			if (gstResponse != null && "1".equals(gstResponse.getStatus_cd())) {

				log.info("GST API responded successfully | application={} | date={}", normalizedApplication, date);

				String decodedData = AESEncryption.baseDecode(gstResponse.getData());

				responseBean = new ObjectMapper().readValue(decodedData, ReturnFileCountResponseBean.class);

				int numFiles = Integer.parseInt(responseBean.getNum_files());

				entity.setNumFiles(numFiles);
				entity.setEodClosed(responseBean.getEod_closed());
				entity.setIsSuccess(true);
				entity.setMsg("SUCCESS");

				/*
				 * RESET ATTEMPT COUNT ON SUCCESS
				 */
				entity.setCounterAttempt(0);

				log.info("File count fetched successfully | application={} | date={} | numFiles={}",
						normalizedApplication, date, numFiles);

			} else {

				/*
				 * STEP-9 : HANDLE FAILURE RESPONSE
				 */
				String errorMessage = commonServiceGstrImplSupport.extractErrorMessage(gstResponse);

				int updatedAttempt = incrementAttempt(entity);

				entity.setIsSuccess(false);
				entity.setMsg(errorMessage);

				log.error("GST API failure | application={} | date={} | attempt={} | error={}", normalizedApplication,
						date, updatedAttempt, errorMessage);
			}

		} catch (JsonProcessingException ex) {

			if (entity != null) {

				int updatedAttempt = incrementAttempt(entity);

				entity.setIsSuccess(false);
				entity.setMsg("JSON_PROCESSING_EXCEPTION : " + ex.getMessage());

				log.error("JSON parsing failed | application={} | date={} | attempt={} | error={}",
						normalizedApplication, date, updatedAttempt, ex.getMessage(), ex);
			}

		} catch (Exception ex) {

			if (entity != null) {

				int updatedAttempt = incrementAttempt(entity);

				entity.setIsSuccess(false);
				entity.setMsg("SYSTEM_EXCEPTION : " + ex.getMessage());

				log.error(
						"Unexpected exception in getReturnFileCount | application={} | date={} | attempt={} | error={}",
						normalizedApplication, date, updatedAttempt, ex.getMessage(), ex);
			}

		} finally {

			try {

				if (entity != null) {

					ReturnFileCountResponse savedEntity = returnFileCountRepository.save(entity);

					responseBean.setReturnFileCountId(savedEntity.getReturnFileCountId());

					log.info(
							"Database record saved successfully | id={} | application={} | date={} | success={} | attempt={}",
							savedEntity.getReturnFileCountId(), savedEntity.getTy(), savedEntity.getDt(),
							savedEntity.getIsSuccess(), savedEntity.getCounterAttempt());
				}

			} catch (Exception dbException) {

				log.error("Database save operation failed | application={} | date={} | error={}", normalizedApplication,
						date, dbException.getMessage(), dbException);
			}

			long totalTime = System.currentTimeMillis() - startTime;

			log.info("END :: getReturnFileCount | application={} | date={} | totalTime={} ms", normalizedApplication,
					date, totalTime);
		}

		return responseBean;
	}

	/*
	 * COMMON METHOD TO INCREMENT ATTEMPT COUNT
	 */
	private int incrementAttempt(ReturnFileCountResponse entity) {

		Integer currentAttempt = entity.getCounterAttempt();

		if (currentAttempt == null) {
			currentAttempt = 0;
		}

		int updatedAttempt = currentAttempt + 1;

		entity.setCounterAttempt(updatedAttempt);

		return updatedAttempt;
	}

	// 1:4
	private String getFileDetails(ReturnFileCountResponseBean returnFileCountResponseBean, MasterData masterData,
			String date, GSTUserSession gstUserSessions, String application, String info, Long returnFileCountId) {

		log.info("▶️ [START] getFileDetails | totalFiles={}, application={}, date={}",
				returnFileCountResponseBean.getNum_files(), application, date);

		long startTime = System.currentTimeMillis();
		String response = StringUtils.EMPTY;

		try {

			APIDetails apiDetails = commonServiceGstrImplSupport.resolveFileDetailsApi(application);// 1:4:1

			HttpHeaders headers = commonServiceGstrImplSupport.getDefaultHeaders(masterData,
					gstUserSessions.getAuthToken(), apiDetails.getApiContentType());// 1:4:2

			int totalFiles = Integer.parseInt(returnFileCountResponseBean.getNum_files());
			String rekFromApi = null; // 👈 add this before if new
			for (int fileNum = totalFiles; fileNum > 0; fileNum--) {

				String fileNumValue = String.valueOf(fileNum);

				if (isAlreadyProcessed(date, application, fileNumValue)) {// 1:4:3
					continue;
				}

				Map<String, String> params = commonServiceGstrImplSupport.getParamsForGetReturnFileDetails(apiDetails,
						masterData, date, application);

				params.put("file_num", fileNumValue);

				String apiPath = authenticationHelper
						.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

				GSTCommonResponseBean responseBean = restClient.get(apiPath, GSTCommonResponseBean.class, headers);
				ReturnFileDetailsResponseBean fileDetails = null;
				// 🔥 Create entity object
				ReturnFileDetailResponse entity = new ReturnFileDetailResponse();
				entity.setReturnFileCountId(returnFileCountId);
				entity.setFileNum(fileNum);
				entity.setDt(date);
				entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

				if (responseBean != null && "1".equals(responseBean.getStatus_cd())) {

					fileDetails = new ObjectMapper().readValue(AESEncryption.baseDecode(responseBean.getData()),
							ReturnFileDetailsResponseBean.class);
					rekFromApi = responseBean.getRek();
					entity.setCnt(Integer.parseInt(fileDetails.getCnt()));
					entity.setUrl(fileDetails.getUrl());
					entity.setHash(fileDetails.getHash());
					entity.setIsSuccess(true);
					entity.setMsg("SUCCESS");

				} else {

					String errorMsg = commonServiceGstrImplSupport.extractErrorMessage(responseBean);

					entity.setIsSuccess(false);
					entity.setMsg(errorMsg);
				}

				// ✅ Save into database
				ReturnFileDetailResponse ReturnFileDetailResponseOutput = returnDetailResponseRepository.save(entity);

				Long returnFilecountId = ReturnFileDetailResponseOutput.getReturnFileCountId();
				Long returnFileDetailId = ReturnFileDetailResponseOutput.getReturnFileDetailId();
				if (application.equalsIgnoreCase("payment")) {
					// Call the method to handle payment files
					response = handlePaymentFiles(fileDetails, gstUserSessions, returnFileCountId, returnFileDetailId,
							rekFromApi, date, fileNum);
				} else {
					response = saveDownloadedFile(fileDetails, date, fileNumValue, application, returnFilecountId,
							returnFileDetailId);
				}

				updateFileProcessStatus(date, application, fileNumValue, response);
			}

		} catch (Exception ex) {
			log.error("🔥 System exception while processing file details | date={}, application={}", date, application,
					ex);
		}

		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("✅ [END] getFileDetails completed | date={}, application={}, time={} ms", date, application,
				timeTaken);
		System.out.println("respone:" + response);
		return response;
	}

	// 1:4:3
//	private boolean isAlreadyProcessed(String date, String application, String fileNum) {
//
//		Optional<DateCountData> existing = dateCountDataRepository.findByDateAndApplicationAndCountAndIsProcessed(date,
//				application, fileNum, "Pass");
//
//		return existing.isPresent();
//	}
	// 1:4:3
	private boolean isAlreadyProcessed(String date, String application, String fileNum) {

		Optional<DateCountData> existingOpt = dateCountDataRepository
				.findTopByDateAndApplicationAndCountOrderByUpdatedDateTimeDesc(date, application, fileNum);

		/*
		 * NO RECORD FOUND
		 */
		if (!existingOpt.isPresent()) {

			log.info("NO_PREVIOUS_RECORD_FOUND | App={} | Date={} | FileNum={}", application, date, fileNum);

			return false;
		}

		DateCountData existing = existingOpt.get();

		String status = existing.getIsProcessed();

		/*
		 * PASS -> SKIP
		 */
		if ("Pass".equalsIgnoreCase(status)) {

			log.info("FILE_ALREADY_PROCESSED_SUCCESSFULLY | App={} | Date={} | FileNum={} | Status={}", application,
					date, fileNum, status);

			return true;
		}

		/*
		 * FAIL -> RETRY
		 */
		log.warn("PREVIOUS_PROCESS_FAILED | RETRYING | App={} | Date={} | FileNum={} | Status={}", application, date,
				fileNum, status);

		return false;
	}

	private String saveDownloadedFile(ReturnFileDetailsResponseBean returnFileDetailsResponseBean, String date,
			String fileNumValue, String application, Long returnFilecountId, Long returnFileDetailId) {

		log.info("▶️ [START] saveDownloadedFile | app={} | date={} | fileNum={}", application, date, fileNumValue);

		Result result = null;

		try {

			if (returnFileDetailsResponseBean != null) {

				log.info("🌐 Download URL received | url={}", returnFileDetailsResponseBean.getUrl());

				long startTime = System.currentTimeMillis();

				result = fileDownloadHelperCommon.downloadAndProcessFileWithOutSaveInDb(
						returnFileDetailsResponseBean.getUrl(), date, fileNumValue, application, returnFilecountId,
						returnFileDetailId);// 1

				long endTime = System.currentTimeMillis();

				log.info(
						"✅ Download & Processing completed | app={} | date={} | fileNum={} | timeTaken={} ms | result={}",
						application, date, fileNumValue, (endTime - startTime), result);

			} else {

				log.error("❌ ReturnFileDetailsResponseBean is NULL | app={} | date={} | fileNum={}", application, date,
						fileNumValue);

				return application + " Return FileDetail Response having issue for date " + date;
			}

		} catch (Exception e) {

			log.error("❌ Exception while downloading & processing file | app={} | date={} | fileNum={} | error={}",
					application, date, fileNumValue, e.getMessage(), e);

			return application + " Exception thrown while processing Download and save at date " + date;
		}

		log.info("🏁 [END] saveDownloadedFile | app={} | date={} | fileNum={} | status=SUCCESS", application, date,
				fileNumValue);

		return result + " Files has been saved successfully for date " + date;
	}

	private void updateFileProcessStatus(String date, String application, String fileNum, String response) {

		Optional<DateCountData> optional = dateCountDataRepository.findByDateAndApplicationAndCount(date, application,
				fileNum);

		DateCountData data = optional.orElseGet(DateCountData::new);

		data.setCount(fileNum);
		data.setDate(date);
		data.setApplication(application);

		data.setIsProcessed(response.contains("success") ? "Pass" : "Fail");

		dateCountDataRepository.save(data);
	}

//=================RECON CODE===================//
	public String getReconFromGstn(String username, String date) {

		log.info("Method : getReconFromGstn");

		MasterData masterData = masterDataService.getMasterdatabyName(username);

		if (masterData == null) {
			log.error("User {} not found in Master Data table", username);
			throw new IllegalStateException("User not found: " + username);
		}

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(username);

		if (gstUserSession == null) {
			log.error("Session not authenticated for user {}", username);
			throw new IllegalStateException(username + " session is not authenticated. Please authenticate.");
		}

		return getReturnFileCount(masterData, date, gstUserSession);
	}

	private String getReturnFileCount(MasterData masterData, String date, GSTUserSession gstUserSessions) {
		String msg = null;
		// String[] rtnTypes = { "R1", "R7", "CM8", "R3B", "R2B" }; // List of rtn_type
		// values
		String[] rtnTypes = { "CM8", "R1", "R1A", "R2B", "R3B", "R4", "R5", "R6", "R7", "R8", "R98A", "R9C", "R9",
				"R9A", "R10", "R11", "ITC02", "EODCIN" }; // List of rtn_type

		try {
			APIDetails apiDetailsForFileCount = apiDetailsImpl.findByName(Constants.GET_RETURN_RECON);

			for (String rtnType : rtnTypes) {
				try {

					HttpHeaders headersForFileCount = authenticationHelper.getDefaultHeaders(masterData,
							gstUserSessions.getAuthToken(), apiDetailsForFileCount.getApiContentType());

					Map<String, String> paramsForFileCount = getParamsForGetReturnFileCount(apiDetailsForFileCount,
							masterData, date);
					paramsForFileCount.put("rtn_type", rtnType); // Set the current rtn_type
					String pathForFileCount = authenticationHelper.getUriWithParam(
							authenticationHelper.getFullPath(masterData, apiDetailsForFileCount), paramsForFileCount);

					GSTCommonResponseBean responseEntity = restClient.get(pathForFileCount, GSTCommonResponseBean.class,
							headersForFileCount);

					if ("1".equals(responseEntity.getStatus_cd())) {
						try {

							byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());

							String decodedDataString = new String(decodedData, "UTF-8"); // Throws checked exception

							Recon recon = new ObjectMapper().readValue(decodedDataString, Recon.class);

							if (recon.getRtnTyp().length() > 1) {
								try {
									reconRepository.save(recon);
									msg = "Files have been saved successfully for rtn_type: " + rtnType;
								} catch (Exception e) {
									log.error("Error while saving Recon data for rtn_type {}: {}", rtnType,
											e.getMessage(), e);
									msg = "Error while saving Recon data for rtn_type: " + rtnType;
								}
							}
						} catch (JsonProcessingException e) {
							log.error("Error while reading JSON data for rtn_type {}: {}", rtnType, e.getMessage(), e);
							msg = "Error while reading JSON data for rtn_type: " + rtnType;
						}
					} else {
						// Handle error response
						String error = responseEntity.getError() != null ? responseEntity.getError().get("message")
								: responseEntity.toString();
						log.error("GST server error for rtn_type {} at {}: {}", rtnType, date, error);
						msg = "GST server error for rtn_type: " + rtnType;
					}
				} catch (Exception e) {
					log.error("Exception while processing Recon for rtn_type {} at date {}: {}", rtnType, date,
							e.getMessage(), e);
					msg = "Exception while processing Recon for rtn_type: " + rtnType;
				}
			}
		} catch (Exception e) {
			log.error("Exception while initializing API details for Recon Download file count: {}", e.getMessage(), e);
			msg = "Exception while initializing API details for Recon Download file count: " + e.getMessage();
		}
		return msg;
	}

	private Map<String, String> getParamsForGetReturnFileCount(APIDetails apiDetails, MasterData masterData,
			String date) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "RECONSUM");
		params.put("state_cd", masterData.getStateCd());
		params.put("filing_dt", date);
		return params;
	}
// new changes for the payment

	private String handlePaymentFiles(ReturnFileDetailsResponseBean fileDetails, GSTUserSession gstUserSessions,
			Long returnFileCountId, Long returnFileDetailId, String rekFromApi, String date, int fileNum) {

		String url = fileDetails.getUrl();
		String sek = gstUserSessions.getSek();

		String response = "Files has been saved successfully";

		List<BaseJsonEntity> batchList = new ArrayList<>(10);
		int sequenceNumber = 1;

		try {

			// STEP 1: Decrypt SEK
			byte[] decryptedSek = GstUtil.decrypt(sek, GstUtil.decodeBase64StringTOByte(gstUserSessions.getAppKey()));

			// STEP 2: Decrypt REK
			byte[] decryptedRek = GstUtil.decrypt(rekFromApi, decryptedSek);

			// STEP 3: Download + extract
			URL fileUrl = new URL(url);
			List<String> fileContents = GstUtil.uncompressTarGZ(fileUrl);

			Date parsedDate = Date.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")));

			for (String content : fileContents) {

				try {

					JsonObject jsonObject = com.google.gson.JsonParser.parseString(content).getAsJsonObject();

					String encryptedData = jsonObject.get("data").getAsString();

					byte[] decryptedData = GstUtil.decrypt(encryptedData, decryptedRek);

					String intermediate = new String(decryptedData, StandardCharsets.UTF_8);

					String finalJson = decodeNestedBase64(intermediate);

					JsonObject outerJson = com.google.gson.JsonParser.parseString(finalJson).getAsJsonObject();

					if (!outerJson.has("data")) {
						continue;
					}

					String decodedInnerData = decodeNestedBase64(outerJson.get("data").getAsString());

					JsonNode decodedJsonNode = objectMapper.readTree(decodedInnerData);

					// 👉 File write
					String fileName = "payment_" + fileNum + "_" + sequenceNumber + ".json";
					// new code

					// 👉 Convert JSON node to string
					String jsonString = objectMapper.writeValueAsString(decodedJsonNode);

					// 👉 Local save (optional - keep if needed)
					// String localPath = writeJsonToFile(paymentFileLocation, date,
					// decodedJsonNode, fileName);

					// 👉 SFTP save
					String remotePath = SftpUtil.uploadJsonFile(jsonString, "Payment_Deloitte", date, fileName);

					// Use remote path in DB
					String filePath = remotePath;

					// new code end

					remotePath = writeJsonToFile(paymentFileLocation, date, decodedJsonNode, fileName);

					// ❗ Skip if file write failed
					if (filePath == null) {
						log.warn("Skipping DB insert because file write failed for CRN file={}", fileName);
						continue;
					}

					BaseJsonEntity entity = commonServiceGstrImplSupport.getEntityByApplication("payment");

					entity.setReturnFileDetailPrimaryId(returnFileDetailId);
					entity.setReturnFileCountPrimaryId(returnFileCountId);
					entity.setFilePath(filePath);
					entity.setJsonData(decodedJsonNode);
					entity.setFileNumber(fileNum);
					entity.setSequenceNumber(sequenceNumber);
					entity.setDt(parsedDate);
					entity.setCategory("payment");
					entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

					batchList.add(entity);
					sequenceNumber++;
					log.info("Batch Size={}", batchList.size());
					// 👉 Batch Save
					if (batchList.size() >= 1) {

						fileDownloadHelperCommon.saveBatchByApplication("payment", batchList);

						log.info("Batch saved successfully size={}", batchList.size());

						batchList.clear();
					}

				} catch (Exception innerEx) {

					log.error("Error processing individual JSON | url={} | error={}", url, innerEx.getMessage(),
							innerEx);
				}
			}

			// 👉 Save remaining
			if (!batchList.isEmpty()) {
				fileDownloadHelperCommon.saveBatchByApplication("payment", batchList);

				log.info("Final batch saved size={}", batchList.size());
			}

		} catch (Exception e) {

			log.error("Error handling payment files | returnFileCountId={} | returnFileDetailId={} | url={} | error={}",
					returnFileCountId, returnFileDetailId, url, e.getMessage(), e);

			response = "fail";
		}

		return response;
	}

	/** Nested Base64 decode utility */
	private String decodeNestedBase64(String input) {
		String result = input;
		boolean decoded = true;

		while (decoded) {
			try {
				byte[] bytes = java.util.Base64.getDecoder().decode(result);
				result = new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
			} catch (IllegalArgumentException e) {
				decoded = false; // stop decoding
			}
		}

		return result;
	}

	private String writeJsonToFile(String basePath, String date, JsonNode jsonData, String fileName) {

		Path directoryPath = null;
		Path filePath = null;

		try {

			// Normalize path (Windows + Linux safe)
			directoryPath = Paths.get(basePath, date).normalize();

			// Create directory if not exists
			if (Files.notExists(directoryPath)) {
				Files.createDirectories(directoryPath);
				log.info("📁 Directory created: {}", directoryPath.toAbsolutePath());
			}

			// Resolve file path
			filePath = directoryPath.resolve(fileName);

			// Avoid overwrite
			if (Files.exists(filePath)) {
				String newFileName = System.currentTimeMillis() + "_" + fileName;
				filePath = directoryPath.resolve(newFileName);
			}

			// Write via temp file (atomic)
			Path tempFile = Files.createTempFile(directoryPath, "tmp_", ".json");

			objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFile.toFile(), jsonData);

			Files.move(tempFile, filePath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

			String finalPath = filePath.toAbsolutePath().toString();

			log.info("📄 JSON successfully written at: {}", finalPath);

			return finalPath;

		} catch (Exception e) {

			log.error("❌ Failed to write JSON | basePath={} | date={} | fileName={} | finalPath={} | error={}",
					basePath, date, fileName, (filePath != null ? filePath.toAbsolutePath() : "N/A"), e.getMessage(),
					e);

			return null; // or throw custom exception if needed
		}
	}
}
