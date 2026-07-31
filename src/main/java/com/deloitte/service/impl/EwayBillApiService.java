package com.deloitte.service.impl;

import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.deloitte.common.bean.DateHepler;
import com.deloitte.common.bean.EwayBillComparisonResponse;
import com.deloitte.common.bean.EwayBillViewResponse;
import com.deloitte.common.bean.FieldComparison;
import com.deloitte.returns.entity.AEwayBill.EWayBillAuthBean;
import com.deloitte.returns.entity.AEwayBill.EwbCountData;
import com.deloitte.returns.entity.AEwayBill.EwbDetailsData;
import com.deloitte.returns.entity.EwayBill.EwayBill_Ewb;
import com.deloitte.returns.entity.EwayBill.PartAEwb;
import com.deloitte.service.abs.AbstractEwayBillApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EwayBillApiService extends AbstractEwayBillApiService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	private static byte[] rekBytes;
	private static byte[] sekBytes;
	private static byte[] appKey = null;

	// 1:
	public String scheduleEwayBillDownload(String category) {

		log.info("▶️ [START] scheduleEwayBillDownload | category={}", category);

		long startTime = System.currentTimeMillis();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			LocalDate endDateLocal = LocalDate.now().minusDays(4);
			String endDate = endDateLocal.format(formatter);

			String startDate = startDateEwaybill;

			log.info("Calculated date range → StartDate={}, EndDate={}", startDate, endDate);

			log.debug("Calling getEwayBillForDateRange()");

			String response = getEwayBillForDateRange(category, startDate, endDate);// 1:1

			long timeTaken = System.currentTimeMillis() - startTime;

			log.info("✅ [SUCCESS] EwayBill download completed | category={} | Time={} ms", category, timeTaken);

			return response + "  StartDate:" + startDate + "  EndDate:" + endDate;

		} catch (Exception ex) {
			log.error("❌ [ERROR] Exception while scheduling eWayBill download | category={}", category, ex);
			throw ex;
		}
	}

	// 1:1
	public String getEwayBillForDateRange(String username, String startDate, String endDate) {

		log.info("▶️ [START] getEwayBillForDateRange | username={}, startDate={}, endDate={}", username, startDate,
				endDate);

		long startTime = System.currentTimeMillis();
		try {
			// Validation
			if (StringUtils.isBlank(username) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
				log.warn("⚠️ Validation failed → username or date parameters are empty");
				return "Username or Dates cannot be empty";
			}

			// Generate Date Range
			List<String> dateList = DateHepler.getDateRange(startDate, endDate);

			if (dateList == null || dateList.isEmpty()) {
				log.warn("⚠️ Date range generation returned empty list | startDate={}, endDate={}", startDate, endDate);
				return "Date list cannot be empty";
			}

			log.info("📅 Date range generated | totalDays={}", dateList.size());
			log.debug("Date list → {}", dateList);

			// Business Processing
			log.info("Calling getEwayBillData() for username={}", username);
			getEwayBillData(username, dateList);// 1:1:1

			long timeTaken = System.currentTimeMillis() - startTime;

			log.info("✅ [SUCCESS] EwayBill data processed successfully | username={} | days={} | time={} ms", username,
					dateList.size(), timeTaken);

			return "E-way Bill Details have been saved for date between " + startDate + " and " + endDate;

		} catch (Exception ex) {
			log.error("❌ [ERROR] Exception while processing EwayBill date range | username={}", username, ex);
			throw ex;
		}
	}

	// 1:1:1
	private void getEwayBillData(String username, List<String> dateList) {

		log.info("▶️ [START] getEwayBillData | username={} | totalDates={}", username, dateList.size());

		long startTime = System.currentTimeMillis();

		try {
			if (CollectionUtils.isEmpty(dateList)) {
				log.warn("⚠️ Date list is empty | Nothing to process");
				return;
			}

			for (String date : dateList) {

				log.debug("Processing date → {}", date);

				String formattedDate = date.replace("-", "/");

				String response = processDateForEwayBill(username, formattedDate);// 1:1:1:1

				log.info("✔ Date processed | username={} | date={} | response={}", username, date, response);
			}

		} catch (Exception ex) {
			log.error("❌ Exception while processing eWayBill data | username={}", username, ex);
			throw ex;

		} finally {
			long timeTaken = System.currentTimeMillis() - startTime;
			log.info("✅ [END] getEwayBillData completed | username={} | time={} ms", username, timeTaken);
		}
	}

	// 1:1:1:1
	private String processDateForEwayBill(String category, String date) {

		log.debug("Checking DB record for category={}, date={}", category, date);

		// DateInfoEwayBill existingDate =
		// dateInfoEwayBillRepository.findDateDataByDateAndCategory(date, category);

		EwbCountData countData = ewbCountDataRepository.findByEwbDtAndEwbCategory(date, category);

		String response = null;

		try {

			// CASE 1 : Record exists
			if (countData != null) {

				log.info("Date already exists in DB. date={}, isSuccess={}", date, countData.getIsSuccess());

				if (Boolean.TRUE.equals(countData.getIsSuccess())) {
					return "Already processed";
				}

				// check failed files
				List<Integer> failedFiles = ewbDetailsDataRepository.findFailedFileNumbers(date, category);

				if (!failedFiles.isEmpty()) {

					log.info("Retrying only failed files : " + failedFiles);

					response = getEWBFileCount(date, category); // will retry only failed

				} else {

					response = getEWBFileCount(date, category);
				}

				return response;
			}

			// CASE 2 : New Date

			EwbCountData newCount = new EwbCountData();

			newCount.setEwbDt(date);
			newCount.setEwbCategory(category);
			newCount.setInsertDt(new Timestamp(System.currentTimeMillis()));
			newCount.setIsSuccess(false);
			newCount.setMsg("PROCESSING");

			ewbCountDataRepository.save(newCount);

			response = getEWBFileCount(date, category);

			return response;

		} catch (

		Exception e) {

			log.error("Error processing date: " + date + " Error: " + e.getMessage());

			return "Processing Failed";
		}
	}

	// =========START REAL WORKING SUPPORT METHOD getEWBFileCount===================
	private HttpHeaders createHeaders(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("client-id", clientId);
		headers.set("client-secret", clientSecret);
		headers.set("statecode", stateCode);
		headers.set("Content-Type", "application/json");
		headers.set("authtoken", authToken);
		return headers;
	}

	private String fetchResponse(String url, String authToken) {

		HttpHeaders headers = createHeaders(authToken);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Failed: HTTP error code: " + response.getStatusCode());
		}

		return response.getBody();
	}

	private String buildEwayUrl(String action, String date, String category) {
		return String.format("%s?action=%s&ewbdt=%s&cat=%s", ewayUrl, action, date, category);
	}

	private String buildEwayUrl(String action, String ewbDt, int genFileCnt, String ewbCategory) {
		return String.format("%s?action=%s&ewbdt=%s&filenum=%d&cat=%s", ewayUrl, action, ewbDt, genFileCnt,
				ewbCategory);
	}

	private static String decrptBySymmetricKeySEK(String encryptedSek) {
		try {
			// Ensure the key is valid (16, 24, or 32 bytes for AES)
			if (appKey == null || (appKey.length != 16 && appKey.length != 24 && appKey.length != 32)) {
				throw new IllegalArgumentException("Invalid AES key size. Key must be 16, 24, or 32 bytes.");
			}

			Key aesKey = new SecretKeySpec(appKey, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] encryptedSekBytes = Base64.getDecoder().decode(encryptedSek);
			byte[] decryptedSekBytes = cipher.doFinal(encryptedSekBytes);
			sekBytes = decryptedSekBytes;
			String decryptedSek = Base64.getEncoder().encodeToString(decryptedSekBytes);
			return decryptedSek;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return "Invalid key size: " + e.getMessage();
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
			return "BadPaddingException: " + e.getMessage() + ". Possible key mismatch or data corruption.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception: " + e.getMessage();
		}
	}

	private static String decryptBySymmentricKeyREK(String encRek) {

		if (sekBytes == null || sekBytes.length == 0) {
			throw new IllegalStateException("sekBytes is NULL. SEK must be decrypted before REK.");
		}

		try {

			Key aesKey = new SecretKeySpec(sekBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.DECRYPT_MODE, aesKey);

			byte[] decodedValue = Base64.getDecoder().decode(encRek);

			byte[] decValue = cipher.doFinal(decodedValue);

			rekBytes = decValue;

			return new String(decValue);

		} catch (Exception e) {
			log.error("Error decrypting REK", e);
			return "Exception " + e;
		}
	}

	private static String decryptBySymmentricKeyData(String data) {

		log.info("Encrypted data: {}", data);

		if (rekBytes == null || rekBytes.length == 0) {
			throw new IllegalStateException("rekBytes not initialized. REK decryption must happen first.");
		}

		try {

			Key aesKey = new SecretKeySpec(rekBytes, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.DECRYPT_MODE, aesKey);

			byte[] decodedValue = Base64.getDecoder().decode(data);

			byte[] decValue = cipher.doFinal(decodedValue);

			return new String(decValue);

		} catch (Exception e) {
			log.error("Error decrypting FILECNT data", e);
			return "Exception " + e;
		}
	}

	private EwbCountData saveCountDataIntoDatabase(String data, String category, String date) {
		try {
			JsonNode jsonNode = objectMapper.readTree(data);
			log.info("Processing file jsonNode: {}", jsonNode);
			// EwbCountData ewbCountData = new EwbCountData();
			EwbCountData ewbCountData = ewbCountDataRepository.findByEwbDtAndEwbCategory(date, category);
			ewbCountData.setEwbDt(jsonNode.get("ewb_dt").asText());
			ewbCountData.setGenFileCnt(jsonNode.get("gen_file_cnt").asInt());
			ewbCountData.setEwbCategory(jsonNode.get("ewb_category").asText());

			// ✅ default false
			ewbCountData.setIsSuccess(false);
			ewbCountData.setMsg("Processing");

			ewbCountData.setInsertDt(new Timestamp(System.currentTimeMillis()));

			return ewbCountDataRepository.save(ewbCountData);
		} catch (Exception e) {
			throw new RuntimeException("Error saving Count data to the database: " + e.getMessage());
		}
	}

	private EwbDetailsData saveDetailsDataIntoDatabase(String data, int fileNum, long countDataId, String category,
			String date) {
		try {
			JsonNode jsonNode = objectMapper.readTree(data);
			EwbDetailsData ewbDetailsData = new EwbDetailsData();
			ewbDetailsData.setEwbDt(jsonNode.get("ewb_dt").asText());
			ewbDetailsData.setEwbCategory(jsonNode.get("ewb_category").asText());
			ewbDetailsData.setFileNum(fileNum);
			ewbDetailsData.setTotalRecords(jsonNode.get("total_records").asInt());
			ewbDetailsData.setUrl(jsonNode.get("url").asText());

			ewbDetailsData.setReturnFileCountId(countDataId);
			ewbDetailsData.setIsSuccess(true);
			ewbDetailsData.setMsg("Success");
			ewbDetailsData.setInsertDt(new Timestamp(System.currentTimeMillis()));

			return ewbDetailsDataRepository.save(ewbDetailsData);
		} catch (IOException e) {
			throw new RuntimeException("Error saving details data: " + e.getMessage());
		}
	}

	private String downloadAndExtractFile(EwbDetailsData detailsData, int fileNum) {
		String fileUrl = detailsData.getUrl(); // Adjust this getter according to your data structure

		// Step 1: Download the file
		ResponseEntity<byte[]> response = downloadFile(fileUrl);
		if (response.getBody() == null) {
			return "Failed to download the file. Empty response body.";
		}

		String gzFile = ewayBillApiSupportService.saveGzFile(response, detailsData, fileNum);

		return "File downloaded and extracted successfully. " + gzFile;
	}

	private HttpHeaders createHeadersForDownload(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("client-id", clientId);
		headers.set("client-secret", clientSecret);
		headers.set("authtoken", authToken);
		headers.set("statecode", stateCode);
		headers.set("Accept-Encoding", "gzip");
		headers.set("AllowAutoRedirect", "false");
		headers.set("KeepAlive", "true");
		return headers;
	}

	private ResponseEntity<byte[]> downloadFile(String fileUrl) {
		EWayBillAuthBean authBean = eWayBillBeanRepository.findFirstByOrderByIdDesc();
		HttpHeaders headers = createHeadersForDownload(authBean.getAuthtoken());

		ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, new HttpEntity<>(headers),
				byte[].class);
		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Failed: HTTP error code: " + response.getStatusCode());
		}
		return response;
	}

	// ✅ ADD THIS METHOD HERE
	private void updateCountStatus(String date, String category, Long countId, int genFileCnt) {

		int successCount = ewbDetailsDataRepository.countSuccessFiles(date, category, countId);

		EwbCountData countData = ewbCountDataRepository.findById(countId).orElse(null);

		if (countData == null) {
			return;
		}

		if (successCount == genFileCnt) {

			countData.setIsSuccess(true);
			countData.setMsg("Success");

		} else {

			countData.setIsSuccess(false);
			countData.setMsg("Some files failed");

		}

		ewbCountDataRepository.save(countData);
	}

	// =========END REAL WORKING SUPPORT METHOD getEWBFileCount===================

//============================REAL WORKING START FROM HERE===================
	// 1:1:1:1:1:1 REAL WORKING START FROM HERE
	public String getEWBFileCount(String date, String category) {

		try {
			log.info("Starting EWB file count process | date: {} | category: {}", date, category);

			// EWayBillAuthBean authBean = supportEwayBillApiService.getLatestAuth();// 1

			String fileDownloadStatus = null;

			log.info("Fetching latest auth token from database");
			EWayBillAuthBean eWayBillAuthBean = eWayBillBeanRepository.findFirstByOrderByIdDesc();

			if (eWayBillAuthBean == null) {
				log.error("Auth token not found in database");
				// return "Auth token missing";
			}

			log.info("Auth token fetched successfully");

			// log.info("Dummy FILECNT URL Example :
			// https://dex.ewaybillgst.gov.in/v1.3/api/ewayApi/GetEWBFile?action=FILECNT&ewbdt=10/04/2022&cat=PARTA");

			log.info("Building FILECNT URL with parameters | action=FILECNT | ewbdt={} | cat={}", date, category);

			String url = buildEwayUrl("FILECNT", date, category);

			log.info("Generated FILECNT URL : {}", url);

			log.info("Calling FILECNT API");
			String responseTextForCount = fetchResponse(url, eWayBillAuthBean.getAuthtoken());
			// log.info("Skipping API call. Using dummy FILECNT response for testing");

			// String responseTextForCount =
			// "{\"status\":\"1\",\"data\":\"kMDcLOfXQA/JCMFL4YmEVJNX0B1z1yNHcduyxALvdqYYpxE6A/GhjLf6OK9RkyUSen8RzLSiopz0Qjt0FIZh6IPuh18AAMMABwauvsV6WdDxtdmpkm3WSyMNPprUoy5G\",\"rek\":\"THDfexjJQB3H/FpNJGGk52/646Y2T011Tk/vSscwAMIfzT6SZ1jROzldiv5NueRD\",\"hmac\":\"w6rI4KnbLUvG4t1DccFrdyipZ5ztX4w4ZPtZGvuPjuY=\"}";

			log.info("FILECNT API Raw Response : {}", responseTextForCount);

			log.info("Parsing FILECNT JSON response");
			JsonNode rootNode = objectMapper.readTree(responseTextForCount);

			log.info("Checking FILECNT API status");

			if ("0".equals(rootNode.get("status").asText())) {

				log.error("FILECNT API returned failure");
				//
				EwbCountData errorData = ewbCountDataRepository.findByEwbDtAndEwbCategory(date, category);
				// EwbCountData errorData = new EwbCountData();
				errorData.setEwbDt(date);
				errorData.setEwbCategory(category);
				errorData.setIsSuccess(false);
				errorData.setMsg("Fail");
				errorData.setInsertDt(new Timestamp(System.currentTimeMillis()));

				log.info("Saving FILECNT failure record in DB");
				ewbCountDataRepository.save(errorData);

				return "FILECNT API Error";
			}

			log.info("Extracting encrypted FILECNT data");// never touch start
			appKey = eWayBillAuthBean.getAppKeyDb();
			String decryptedSekOne = decrptBySymmetricKeySEK(eWayBillAuthBean.getSek());
			String encDataForCount = rootNode.get("data").asText();
			String encRekForCount = rootNode.get("rek").asText();

			log.info("Decrypting REK for FILECNT");
			String rek = decryptBySymmentricKeyREK(encRekForCount);

			log.info("Decrypting FILECNT data");
			String dataForCount = decryptBySymmentricKeyData(encDataForCount);// never touch end

			log.info("Saving FILECNT data into database");
			EwbCountData countData = saveCountDataIntoDatabase(dataForCount, category, date);

			int numFiles = countData.getGenFileCnt();

			log.info("Total files returned by FILECNT API : {}", numFiles);

			for (int fileNum = numFiles; fileNum > 0; fileNum--) {

				log.info("----------------------------------------------------");
				log.info("Processing FILEDET for fileNum = {}", fileNum);

				log.info("Loop values | ewbDt={} | category={} | fileNum={}", countData.getEwbDt(),
						countData.getEwbCategory(), fileNum);

				// log.info(
//						"Dummy FILEDET URL Example :https://dex.ewaybillgst.gov.in/v1.3/api/ewayApi/GetEWBFile?action=FILEDET&ewbdt=10/04/2022&filenum=4&cat=PARTA");

				// log.info("Building FILEDET URL");

				String detailUrl = buildEwayUrl("FILEDET", countData.getEwbDt(), fileNum, countData.getEwbCategory());

				log.info("Generated FILEDET URL : {}", detailUrl);

				log.info("Calling FILEDET API");

				String responseTextDetails = fetchResponse(detailUrl, eWayBillAuthBean.getAuthtoken());

				log.info("FILEDET API Raw Response for fileNum {} : {}", fileNum, responseTextDetails);

				log.info("Parsing FILEDET JSON response");

				JsonNode detailNode = objectMapper.readTree(responseTextDetails);

				if ("0".equals(detailNode.get("status").asText())) {

					String apiMsg = detailNode.path("error").asText("Unknown API Error");

					log.error("FILEDET API error | fileNum={} | msg={}", fileNum, apiMsg);

					EwbDetailsData errorDetails = new EwbDetailsData();

					errorDetails.setEwbDt(date);
					errorDetails.setEwbCategory(category);
					errorDetails.setFileNum(fileNum);
					errorDetails.setReturnFileCountId(countData.getId());
					errorDetails.setIsSuccess(false);
					errorDetails.setMsg(apiMsg);
					errorDetails.setInsertDt(new Timestamp(System.currentTimeMillis()));

					log.info("Saving FILEDET error record into DB");

					ewbDetailsDataRepository.save(errorDetails);

					continue;
				}

				log.info("Extracting encrypted FILEDET data");

				String encDataDetails = detailNode.get("data").asText();
				String encRekDetails = detailNode.get("rek").asText();

				log.info("Decrypting FILEDET REK");
				String rekDetails = decryptBySymmentricKeyREK(encRekDetails);

				log.info("Decrypting FILEDET data");
				String dataDetails = decryptBySymmentricKeyData(encDataDetails);

				log.info("Saving FILEDET details into database");

				EwbDetailsData detailsData = saveDetailsDataIntoDatabase(dataDetails, fileNum, countData.getId(),
						category, date);

				log.info("Downloading and extracting file for fileNum = {}", fileNum);

				fileDownloadStatus = downloadAndExtractFile(detailsData, fileNum);

				log.info("Download status for fileNum {} : {}", fileNum, fileDownloadStatus);
			}

			log.info("All files processed. Updating count status");

			updateCountStatus(date, category, countData.getId(), numFiles);

			log.info("File download completed successfully for date={} category={}", date, category);

			return "File download completed";

		} catch (Exception e) {

			log.error("Exception occurred in getEWBFileCount method", e);

			return "Error processing EWB File Count: " + e.getMessage();
		}
	}

	public EwayBillViewResponse getEwayBill(long ewbNo) {

		EwayBill_Ewb ewb = ewayBillEwbRepository.findFirstByEwbNoOrderByIdDesc(ewbNo)
				.orElseThrow(() -> new RuntimeException("EWay Bill not found"));

		return EwayBillViewResponse.builder()

				.ewaybillNo(ewb.getEwbNo()).ewaybillDate(ewb.getEwbDt())

				.frmGstin(ewb.getFrGstin()).frmName(ewb.getFrName()).frmState(getStateName(ewb.getFrStat()))

				.toGstin(ewb.getToGstin()).toName(ewb.getToName()).toState(getStateName(ewb.getToStat()))

				.docNo(ewb.getDocNo()).docDt(ewb.getDocDt())

				.assessableValue(ewb.getAssVal())

				.igst(ewb.getIgstVal()).cgst(ewb.getCgstVal()).sgst(ewb.getSgstVal()).cess(ewb.getCessVal())

				.status(getStatus(ewb.getStatus()))

				.build();
	}

	private String getStatus(String status) {

		switch (status) {

		case "ACT":
			return "ACT - Active";

		case "CNL":
			return "CNL - Cancelled";

		case "EXP":
			return "EXP - Expired";

		default:
			return status;
		}
	}

	public Page<EwayBillViewResponse> searchByDate(String docDate, String status, int page, int size) {

		LocalDate date = LocalDate.parse(docDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		Pageable pageable = PageRequest.of(page, size);

		Page<EwayBill_Ewb> bills;

		if ("ALL".equalsIgnoreCase(status)) {
			bills = ewayBillEwbRepository.findByDocDate(formattedDate, pageable);
		} else {
			bills = ewayBillEwbRepository.findByDocDateAndStatus(formattedDate, status, pageable);
		}

		return bills.map(this::convertToResponse);
	}

	private EwayBillViewResponse convertToResponse(EwayBill_Ewb ewb) {

		EwayBillViewResponse response = new EwayBillViewResponse();

		response.setEwaybillNo(ewb.getEwbNo());
		response.setStatus(ewb.getStatus());
		response.setEwaybillDate(ewb.getEwbDt());

		response.setFrmGstin(ewb.getFrGstin());
		response.setFrmName(ewb.getFrName());
		response.setFrmState(getStateName(ewb.getFrStat()));

		response.setToGstin(ewb.getToGstin());
		response.setToName(ewb.getToName());
		response.setToState(getStateName(ewb.getToStat()));

		response.setDocNo(ewb.getDocNo());
		response.setDocDt(ewb.getDocDt());

		response.setAssessableValue(ewb.getAssVal());
		response.setIgst(ewb.getIgstVal());
		response.setCgst(ewb.getCgstVal());
		response.setSgst(ewb.getSgstVal());
		response.setCess(ewb.getCessVal());

		return response;

	}

	public static String getStateName(Long stateCode) {

		if (stateCode == null) {
			return "";
		}

		Map<Long, String> stateMap = new HashMap<>();

		stateMap.put(1L, "Jammu & Kashmir");
		stateMap.put(2L, "Himachal Pradesh");
		stateMap.put(3L, "Punjab");
		stateMap.put(4L, "Chandigarh");
		stateMap.put(5L, "Uttarakhand");
		stateMap.put(6L, "Haryana");
		stateMap.put(7L, "Delhi");
		stateMap.put(8L, "Rajasthan");
		stateMap.put(9L, "Uttar Pradesh");
		stateMap.put(10L, "Bihar");
		stateMap.put(11L, "Sikkim");
		stateMap.put(12L, "Arunachal Pradesh");
		stateMap.put(13L, "Nagaland");
		stateMap.put(14L, "Manipur");
		stateMap.put(15L, "Mizoram");
		stateMap.put(16L, "Tripura");
		stateMap.put(17L, "Meghalaya");
		stateMap.put(18L, "Assam");
		stateMap.put(19L, "West Bengal");
		stateMap.put(20L, "Jharkhand");
		stateMap.put(21L, "Odisha");
		stateMap.put(22L, "Chhattisgarh");
		stateMap.put(23L, "Madhya Pradesh");
		stateMap.put(24L, "Gujarat");
		stateMap.put(26L, "Dadra & Nagar Haveli and Daman & Diu");
		stateMap.put(27L, "Maharashtra");
		stateMap.put(29L, "Karnataka");
		stateMap.put(30L, "Goa");
		stateMap.put(31L, "Lakshadweep");
		stateMap.put(32L, "Kerala");
		stateMap.put(33L, "Tamil Nadu");
		stateMap.put(34L, "Puducherry");
		stateMap.put(35L, "Andaman & Nicobar Islands");
		stateMap.put(36L, "Telangana");
		stateMap.put(37L, "Andhra Pradesh");
		stateMap.put(38L, "Ladakh");
		stateMap.put(97L, "Other Territory");
		stateMap.put(99L, "Other Country");

		return stateMap.getOrDefault(stateCode, "Unknown State");
	}

//	public EwayBillComparisonResponse compare(long ewbNo) {
//
//		// New Table
//		EwayBill_Ewb newEwb = ewayBillEwbRepository.findFirstByEwbNo(ewbNo)
//				.orElseThrow(() -> new RuntimeException("New Data Not Found"));
//
//		// Old Table
//		String ewbNoStr = String.valueOf(ewbNo);
//		PartAEwb oldEwb = partAEwbRepository.findFirstByEwbNo(ewbNoStr)
//				.orElseThrow(() -> new RuntimeException("Old Data Not Found"));
//
//		EwayBillViewResponse newData = mapNewData(newEwb);
//
//		EwayBillViewResponse oldData = mapOldData(oldEwb);
//
//		List<FieldComparison> comparisons = new ArrayList<>();
//
//		comparisons.add(compareField("EWB Number", newData.getEwaybillNo(), oldData.getEwaybillNo()));
//
//		comparisons.add(
//				compareField("Status", normalizeStatus(newData.getStatus()), normalizeStatus(oldData.getStatus())));
//
//		comparisons.add(compareField("EWB Date", newData.getEwaybillDate(), oldData.getEwaybillDate()));
//
//		comparisons.add(compareField("From GSTIN", newData.getFrmGstin(), oldData.getFrmGstin()));
//
//		comparisons.add(compareField("From Name", newData.getFrmName(), oldData.getFrmName()));
//
//		comparisons.add(compareField("From State", newData.getFrmState(), oldData.getFrmState()));
//
//		comparisons.add(compareField("To GSTIN", newData.getToGstin(), oldData.getToGstin()));
//
//		comparisons.add(compareField("To Name", newData.getToName(), oldData.getToName()));
//
//		comparisons.add(compareField("To State", newData.getToState(), oldData.getToState()));
//
//		comparisons.add(compareField("Document No", newData.getDocNo(), oldData.getDocNo()));
//
//		comparisons.add(compareField("Document Date", newData.getDocDt(), oldData.getDocDt()));
//
//		comparisons.add(compareField("Assessable Value", newData.getAssessableValue(), oldData.getAssessableValue()));
//
//		comparisons.add(compareField("IGST", newData.getIgst(), oldData.getIgst()));
//
//		comparisons.add(compareField("CGST", newData.getCgst(), oldData.getCgst()));
//
//		comparisons.add(compareField("SGST", newData.getSgst(), oldData.getSgst()));
//
//		comparisons.add(compareField("CESS", newData.getCess(), oldData.getCess()));
//
//		return EwayBillComparisonResponse.builder().newData(newData).oldData(oldData).comparisons(comparisons).build();
//	}
	public EwayBillComparisonResponse compare(long ewbNo) {

		// ============================
		// New Table (Mandatory)
		// ============================
		EwayBill_Ewb newEwb = ewayBillEwbRepository.findFirstByEwbNoOrderByIdDesc(ewbNo)
				.orElseThrow(() -> new RuntimeException("New Data Not Found"));

		EwayBillViewResponse newData = mapNewData(newEwb);

		// ============================
		// Old Table (Optional)
		// ============================
		String ewbNoStr = String.valueOf(ewbNo);

		Optional<PartAEwb> oldEwbOptional = partAEwbRepository.findFirstByEwbNo(ewbNoStr);

		EwayBillViewResponse oldData = oldEwbOptional.map(this::mapOldData).orElse(null);

		List<FieldComparison> comparisons = new ArrayList<>();

		if (oldData == null) {

			comparisons.add(compareField("EWB Number", newData.getEwaybillNo(), "Not Found"));
			comparisons.add(compareField("Status", normalizeStatus(newData.getStatus()), "Not Found"));
			comparisons.add(compareField("EWB Date", newData.getEwaybillDate(), "Not Found"));

			comparisons.add(compareField("From GSTIN", newData.getFrmGstin(), "Not Found"));
			comparisons.add(compareField("From Name", newData.getFrmName(), "Not Found"));
			comparisons.add(compareField("From State", newData.getFrmState(), "Not Found"));

			comparisons.add(compareField("To GSTIN", newData.getToGstin(), "Not Found"));
			comparisons.add(compareField("To Name", newData.getToName(), "Not Found"));
			comparisons.add(compareField("To State", newData.getToState(), "Not Found"));

			comparisons.add(compareField("Document No", newData.getDocNo(), "Not Found"));
			comparisons.add(compareField("Document Date", newData.getDocDt(), "Not Found"));

			comparisons.add(compareField("Assessable Value", newData.getAssessableValue(), "Not Found"));
			comparisons.add(compareField("IGST", newData.getIgst(), "Not Found"));
			comparisons.add(compareField("CGST", newData.getCgst(), "Not Found"));
			comparisons.add(compareField("SGST", newData.getSgst(), "Not Found"));
			comparisons.add(compareField("CESS", newData.getCess(), "Not Found"));

		} else {

			comparisons.add(compareField("EWB Number", newData.getEwaybillNo(), oldData.getEwaybillNo()));

			comparisons.add(
					compareField("Status", normalizeStatus(newData.getStatus()), normalizeStatus(oldData.getStatus())));

			comparisons.add(compareField("EWB Date", newData.getEwaybillDate(), oldData.getEwaybillDate()));

			comparisons.add(compareField("From GSTIN", newData.getFrmGstin(), oldData.getFrmGstin()));
			comparisons.add(compareField("From Name", newData.getFrmName(), oldData.getFrmName()));
			comparisons.add(compareField("From State", newData.getFrmState(), oldData.getFrmState()));

			comparisons.add(compareField("To GSTIN", newData.getToGstin(), oldData.getToGstin()));
			comparisons.add(compareField("To Name", newData.getToName(), oldData.getToName()));
			comparisons.add(compareField("To State", newData.getToState(), oldData.getToState()));

			comparisons.add(compareField("Document No", newData.getDocNo(), oldData.getDocNo()));
			comparisons.add(compareField("Document Date", newData.getDocDt(), oldData.getDocDt()));

			comparisons
					.add(compareField("Assessable Value", newData.getAssessableValue(), oldData.getAssessableValue()));

			comparisons.add(compareField("IGST", newData.getIgst(), oldData.getIgst()));
			comparisons.add(compareField("CGST", newData.getCgst(), oldData.getCgst()));
			comparisons.add(compareField("SGST", newData.getSgst(), oldData.getSgst()));
			comparisons.add(compareField("CESS", newData.getCess(), oldData.getCess()));
		}

		return EwayBillComparisonResponse.builder().newData(newData).oldData(oldData).comparisons(comparisons).build();
	}

	private EwayBillViewResponse mapNewData(EwayBill_Ewb ewb) {

		return EwayBillViewResponse.builder()

				.ewaybillNo(ewb.getEwbNo()).ewaybillDate(ewb.getEwbDt())

				.frmGstin(ewb.getFrGstin()).frmName(ewb.getFrName()).frmState(getStateName(ewb.getFrStat()))

				.toGstin(ewb.getToGstin()).toName(ewb.getToName()).toState(getStateName(ewb.getToStat()))

				.docNo(ewb.getDocNo()).docDt(ewb.getDocDt())

				.assessableValue(ewb.getAssVal())

				.igst(ewb.getIgstVal()).cgst(ewb.getCgstVal()).sgst(ewb.getSgstVal()).cess(ewb.getCessVal())

				.status(getStatus(ewb.getStatus()))

				.build();
	}

	private EwayBillViewResponse mapOldData(PartAEwb ewb) {

		return EwayBillViewResponse.builder()

				.ewaybillNo(Long.parseLong(ewb.getEwbNo())).ewaybillDate(ewb.getEwbDt())

				.frmState(getStateName(ewb.getFrStat() == null ? null : ewb.getFrStat().longValue()))

				.toGstin(ewb.getToGstin()).toName(ewb.getToName()).frmGstin(ewb.getFrGstin()).frmName(ewb.getFrName())
				.toState(getStateName(ewb.getToStat() == null ? null : ewb.getToStat().longValue()))

				.docNo(ewb.getDocNo()).docDt(ewb.getDocDt())

				.assessableValue(ewb.getAssVal().doubleValue())

				.igst(ewb.getIgstVal().doubleValue()).cgst(ewb.getCgstVal().doubleValue())
				.sgst(ewb.getSgstVal().doubleValue()).cess(ewb.getCessVal().doubleValue())

				.status(ewb.getStatus())

				.build();
	}

	private String normalizeStatus(String status) {

		if (status == null || status.trim().isEmpty()) {
			return "";
		}

		status = status.trim().toUpperCase();

		switch (status) {

		case "ACT":
		case "ACTIVE":
		case "ACT - ACTIVE":
			return "ACTIVE";

		case "CNL":
		case "CANCELLED":
		case "CNL - CANCELLED":
			return "CANCELLED";

		case "EXP":
		case "EXPIRED":
		case "EXP - EXPIRED":
			return "EXPIRED";

		default:
			return status;
		}
	}

	private FieldComparison compareField(String field, Object newValue, Object oldValue) {

		String newVal = newValue == null ? "" : newValue.toString().trim();
		String oldVal = oldValue == null ? "" : oldValue.toString().trim();

		return FieldComparison.builder().field(field).newValue(newVal).oldValue(oldVal)
				.matched(newVal.equalsIgnoreCase(oldVal)).build();
	}

}
