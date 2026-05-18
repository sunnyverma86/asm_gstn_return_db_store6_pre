package com.deloitte.service.support;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Key;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.Result;
import com.deloitte.returns.entity.AEwayBill.DateEwayGzFilePath;
import com.deloitte.returns.entity.AEwayBill.EWayBillAuthBean;
import com.deloitte.returns.entity.AEwayBill.EwayFileCountResponse;
import com.deloitte.returns.entity.AEwayBill.EwayFileDetailResponse;
import com.deloitte.returns.entity.AEwayBill.EwayGzJsonStorage;
import com.deloitte.returns.entity.AEwayBill.EwbCountData;
import com.deloitte.returns.entity.AEwayBill.EwbDetailsData;
import com.deloitte.service.abs.AbstractSupportEwayBillApiService;

import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Log4j2
public class SupportEwayBillApiService extends AbstractSupportEwayBillApiService {

	// 1
	public EWayBillAuthBean getLatestAuth() {

		log.info("Fetching latest EWB auth token");

		EWayBillAuthBean authBean = eWayBillBeanRepository.findFirstByOrderByIdDesc();

		if (authBean == null) {
			log.error("No EWB authentication record found in database");
			throw new RuntimeException("EWB authentication not available");
		}

//		if (isTokenExpired(authBean)) {//1:1
//			log.warn("EWB auth token expired | id: {}", authBean.getId());
//			throw new RuntimeException("EWB authentication token expired");
//		}

		log.info("EWB auth token fetched successfully | id: {}", authBean.getId());

		return authBean;
	}

	// 2
	public JsonNode fetchFileCountResponse(String date, String category, EWayBillAuthBean authBean) throws IOException {
		log.info("fetchFileCountResponse");
		String url = buildEwayUrl("FILECNT", date, category);// 2:1
		String responseTextForCount = fetchResponse(url, authBean.getAuthtoken());
		log.info("url:"+url);
		return objectMapper.readTree(responseTextForCount);
	}

	// 3
	public EwayFileCountResponse handleFileCountResponse(JsonNode rootNode, String date) throws IOException {
		log.info("handleFileCountResponse");
		EwayFileCountResponse entity = new EwayFileCountResponse();
		entity.setDt(date);
		entity.setInsertDt(new Timestamp(System.currentTimeMillis()));
		log.info("handleFileCountResponse:1");
		if (isFailure(rootNode)) { // 3:1
			entity.setIsSuccess(false);
			entity.setMsg(rootNode.path("message").asText("Failed"));
			log.info("handleFileCountResponse Failed:::"+entity.getIsSuccess());
			ewayFileCountResponseRepository.save(entity);
			return entity;
		}
		
		entity.setIsSuccess(true);
		entity.setMsg("Success");
		log.info("handleFileCountResponse Success:::"+entity.getIsSuccess());
		String decryptedData = decryptResponse(rootNode); // 3:2
		log.info("decryptedData :::"+decryptedData);
		JsonNode decryptedNode = objectMapper.readTree(decryptedData);
		log.info("decryptedData :::"+decryptedNode);
		entity.setTy(decryptedNode.path("ty").asText(null));
		entity.setEodClosed(decryptedNode.path("eod_closed").asText(null));
		entity.setNumFiles(decryptedNode.path("num_files").asInt(0));

		return ewayFileCountResponseRepository.save(entity);
	}

	// 4
	public EwbCountData saveCountData(JsonNode countResponse) {

		try {

			// 🔹 Decrypt response
			String decryptedData = decryptResponse(countResponse);// 3:2

			JsonNode jsonNode = objectMapper.readTree(decryptedData);

			EwbCountData ewbCountData = new EwbCountData();
			ewbCountData.setEwbDt(jsonNode.path("ewb_dt").asText(null));
			ewbCountData.setGenFileCnt(jsonNode.path("gen_file_cnt").asInt(0));
			ewbCountData.setEwbCategory(jsonNode.path("ewb_category").asText(null));

			EwbCountData savedData = ewbCountDataRepository.save(ewbCountData);

			log.info("EWB Count data saved | id: {} | date: {} | fileCount: {}", savedData.getId(),
					savedData.getEwbDt(), savedData.getGenFileCnt());

			return savedData;

		} catch (Exception e) {
			log.error("Error saving EWB count data", e);
			throw new RuntimeException("Failed to save EWB count data");
		}
	}

	// 5
	public void processFileDetails(EwbCountData countData, EWayBillAuthBean authBean, String date, String category,
			Long parentId) throws IOException {

		for (int fileNum = countData.getGenFileCnt(); fileNum > 0; fileNum--) {

			JsonNode detailResponse = fetchFileDetailResponse(authBean, countData, fileNum);// 5:1

			handleFileDetail(detailResponse, fileNum, date, category, parentId);// 5:2
		}
	}

	// support method
	// 1:1
//	private boolean isTokenExpired(EWayBillAuthBean authBean) {
//
//		if (authBean.getTokenExpiry() == null) {
//			return true;
//		}
//
//		return authBean.getTokenExpiry().before(new java.util.Date());
//	}

	// 2:1
	private String buildEwayUrl(String action, String date, String category) {
		return String.format("%s?action=%s&ewbdt=%s&cat=%s", ewayUrl, action, date, category);
	}

	// 2:2
	private String fetchResponse(String url, String authToken) {

		HttpHeaders headers = createHeaders(authToken);// 2:2:1

		log.info("==============================================");
		log.info("▶ HTTP Request Details");
		log.info("Method : GET");
		log.info("URL    : {}", url);
		headers.forEach((key, values) -> log.info("  {}: {}", key, values));
		log.info("==============================================");

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		log.info("▶ HTTP Response Details");
		log.info("Status Code : {}", response.getStatusCode().value());
		log.info("Headers     : {}", response.getHeaders());
		log.info("Body        : {}", response.getBody());
		log.info("==============================================");

		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Failed: HTTP error code: " + response.getStatusCode().value());
		}

		return response.getBody();
	}

	// 2:2:1
	private HttpHeaders createHeaders(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("client-id", clientId);
		headers.set("client-secret", clientSecret);
		headers.set("statecode", stateCode);
		headers.set("Content-Type", "application/json");
		headers.set("authtoken", authToken);
		return headers;
	}

	// 3:1
	private boolean isFailure(JsonNode node) {
		return "0".equals(node.path("status").asText());
	}

	// 3:2
	private String decryptResponse(JsonNode detailNodeValue) {
		log.info("decryptResponse Success1:::"+detailNodeValue);
		log.info("decryptResponse Success2:::"+ detailNodeValue.path("data").asText());
		log.info("decryptResponse Success3:::"+  detailNodeValue.path("rek").asText());
	    String encDataDetails = detailNodeValue.path("data").asText();
	    String encRekDetails = detailNodeValue.path("rek").asText();
	    String rekDetails = decryptBySymmentricKeyREK(encRekDetails);
		log.info("decryptResponse Success4:::"+ rekDetails);
	    
	    String dataDetails = decryptBySymmentricKeyData(encDataDetails);

	    return dataDetails;
	}
	// 3:2:1
	public static String decryptBySymmentricKeyREK(String encRek) {
		log.info("decryptResponse Success5:::"+encRek);
		Key aesKey = new SecretKeySpec(sekBytes, "AES");// 3.2
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decodedValue = Base64.getDecoder().decode(encRek);
			byte[] decValue = cipher.doFinal(decodedValue);
			rekBytes = decValue;// 3
			return new String(decValue);
		} catch (Exception e) {
			log.info("Fail Exception:::"+encRek);
			return "Exception " + e;
		}
	}

	// //3:2:2 encryption code here start
	// encryption code here start
	public static String decryptBySymmentricKeyData(String data) {

		Key aesKey = new SecretKeySpec(rekBytes, "AES");// 2
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decodedValue = Base64.getDecoder().decode(data);
			byte[] decValue = cipher.doFinal(decodedValue);
			return new String(decValue);
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception " + e;
		}
	}

	// 5:1
	private JsonNode fetchFileDetailResponse(EWayBillAuthBean authBean, EwbCountData countData, int fileNum)
			throws IOException {

		String url = buildEwayUrl("FILEDET", countData.getEwbDt(), fileNum, countData.getEwbCategory());// 5:1:1

		log.info("Calling FILEDET API | fileNum: {} | date: {}", fileNum, countData.getEwbDt());

		String response = fetchResponse(url, authBean.getAuthtoken());// 2:2

		return objectMapper.readTree(response);
	}

	// 5:1:1
	private String buildEwayUrl(String action, String ewbDt, int genFileCnt, String ewbCategory) {
		return String.format("%s?action=%s&ewbdt=%s&filenum=%d&cat=%s", ewayUrl, action, ewbDt, genFileCnt,
				ewbCategory);
	}

	// 5:2
	private void handleFileDetail(JsonNode detailNode, int fileNum, String date, String category, Long parentId)
			throws IOException {

		EwayFileDetailResponse detailEntity = new EwayFileDetailResponse();
		detailEntity.setReturnFileCountId(parentId);
		detailEntity.setFileNum(fileNum);
		detailEntity.setDt(date);
		detailEntity.setInsertDt(new Timestamp(System.currentTimeMillis()));

		// 🔹 FAILURE CASE
		if (isFailure(detailNode)) {// 3:1

			detailEntity.setIsSuccess(false);
			detailEntity.setMsg(detailNode.path("message").asText("Failed"));
			detailEntity.setCnt(0);
			detailEntity.setUrl(null);

			ewayFileDetailResponseRepository.save(detailEntity);

			log.warn("FILEDET failed | fileNum: {} | msg: {}", fileNum, detailEntity.getMsg());

			return;
		}

		// 🔹 SUCCESS CASE
		detailEntity.setIsSuccess(true);
		detailEntity.setMsg("Success");

		String decryptedData = decryptResponse(detailNode);// 3:2

		JsonNode decryptedNode = objectMapper.readTree(decryptedData);

		detailEntity.setCnt(decryptedNode.path("cnt").asInt(0));
		detailEntity.setUrl(decryptedNode.path("url").asText(null));

		EwayFileDetailResponse savedEntity = ewayFileDetailResponseRepository.save(detailEntity);

		log.info("FILEDET saved successfully | id: {} | fileNum: {}", savedEntity.getReturnFileDetailId(), fileNum);

		// 🔹 Save details data
		EwbDetailsData detailsData = saveDetailsDataIntoDatabase(decryptedData, fileNum);// 5:2:1
		Result result = null;
		// 🔹 Download and process file
		result = downloadAndExtractFile(detailsData, fileNum, category, date, savedEntity.getReturnFileDetailId(),
				parentId);// 5:2:2

		log.info("File processing status | fileNum: {} | result: {}", fileNum, result);
	}

	// 5:2:1
	private EwbDetailsData saveDetailsDataIntoDatabase(String data, int fileNum) {
		JsonNode jsonNode = objectMapper.readTree(data);
		EwbDetailsData ewbDetailsData = new EwbDetailsData();
		ewbDetailsData.setEwbDt(jsonNode.get("ewb_dt").asText());
		ewbDetailsData.setEwbCategory(jsonNode.get("ewb_category").asText());
		ewbDetailsData.setFileNum(fileNum);
		ewbDetailsData.setTotalRecords(jsonNode.get("total_records").asInt());
		ewbDetailsData.setUrl(jsonNode.get("url").asText());
		return ewbDetailsDataRepository.save(ewbDetailsData);
	}

	// 5:2:2
	private Result downloadAndExtractFile(EwbDetailsData detailsData, int fileNum, String category, String date,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimarId) {
		long start = System.currentTimeMillis();

		log.info("▶ START | app:EwayBill={} | date={} | file={} | url={}", category, date, fileNum,
				detailsData.getUrl());
		String fileUrl = detailsData.getUrl();
		try {

			// 1️⃣ Validate input
			if (fileUrl == null || fileUrl.isBlank()) {
				return new Result(null, "Invalid file URL.");
			}

			if (fileNum <= 0) {
				return new Result(null, "Invalid file number.");
			}

			String message = downloadAndStoreGzFile(fileUrl, fileNum, category, date, returnFileDetailPrimaryId,
					returnFileCountPrimarId);

			long time = System.currentTimeMillis() - start;

			if (message != null && message.toLowerCase().contains("success")) {

				log.info("✔ SUCCESS | app={} | date={} | file={} | time={}ms | msg={}", category, date, fileNum, time,
						message);

				return new Result("SUCCESS", message);

			} else {

				log.warn("⚠ PROCESS COMPLETED WITH MESSAGE | app={} | date={} | file={} | time={}ms | msg={}", category,
						date, fileNum, time, message);

				return new Result(null, message);
			}

		} catch (Exception ex) {

			long time = System.currentTimeMillis() - start;

			log.error("🔥 CRITICAL FAILURE | app={} | date={} | file={} | time={}ms", category, date, fileNum, time,
					ex);

			return new Result(null, "Error: " + ex.getMessage());
		}
	}

	// 5:2:2:1
	private String downloadAndStoreGzFile(String fileUrl, int fileNum, String application, String date,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimaryId) {

		if (fileUrl == null || fileUrl.isBlank()) {
			return "Invalid file URL.";
		}

		if (application == null || application.isBlank()) {
			return "Invalid application name.";
		}

		if (date == null || date.isBlank()) {
			return "Invalid date.";
		}

		try {

			// 1️⃣ Create base directory (Better: configure from application.properties)
			String baseDir = resolveDirectoryPath(application); // your existing method
			Path appPath = Paths.get(baseDir);
			Path dateFolder = appPath.resolve(date);
			Files.createDirectories(dateFolder);

			// 2️⃣ Create file name
			String fileName = date + "_" + fileNum + "_downloaded_file.gz";
			Path gzPath = dateFolder.resolve(fileName);

			log.info("📂 Target GZ Path: {}", gzPath.toAbsolutePath());

			// 3️⃣ If file already exists
			if (Files.exists(gzPath)) {
				log.info("⚠ File already exists: {}", gzPath.getFileName());
				return "File already downloaded.";
			}

			// 4️⃣ Download using stream (memory safe)
			downloadWithRetry(fileUrl, gzPath);

			log.info("✅ GZ File saved successfully at: {}", gzPath.toAbsolutePath());

			// 5️⃣ Extract and store JSON into DB
			extractAndStoreJson(gzPath.toFile(), fileNum, application, date, returnFileDetailPrimaryId,
					returnFileCountPrimaryId);

			return "File downloaded and processed successfully.";

		} catch (Exception e) {
			log.error("❌ Error while downloading file | URL={} | Reason={}", fileUrl, e.getMessage(), e);
			return "Error while processing file: " + e.getMessage();
		}
	}

	// 5:2:2:1:1
	private String resolveDirectoryPath(String application) {

		if (application == null || application.trim().isEmpty()) {
			throw new IllegalArgumentException("Application cannot be null or empty");
		}
		String folderName = application.trim().toUpperCase();
		Path basePath = Paths.get(ewayBillFileGzFile);
		Path finalPath = basePath.resolve(folderName);

		return finalPath.toString();
	}

	// 5:2:2:1:2
	private void downloadWithRetry(String url, Path targetPath) throws IOException {

		int attempt = 1;

		while (attempt <= HARD_MAX_RETRY) {

			try (InputStream in = new BufferedInputStream(new URL(url).openStream())) {

				Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
				log.info("✔ Downloaded: {}", targetPath.getFileName());
				return;

			} catch (IOException ex) {

				if (attempt == HARD_MAX_RETRY) {
					throw ex;
				}

				log.warn("⚠ Attempt {}/{} failed. Retrying... | Reason={}", attempt, HARD_MAX_RETRY, ex.getMessage());

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attempt++;
			}
		}
	}

	// 5:2:2:1:3
	private void extractAndStoreJson(File gzFile, int fileNum, String application, String date,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimaryId) throws Exception {

		long start = System.currentTimeMillis();
		log.info("▶ START TAR.GZ Extraction | file={}", gzFile.getAbsolutePath());

		ObjectMapper objectMapper = new ObjectMapper();
		List<EwayGzJsonStorage> batchList = new ArrayList<>();
		int sequenceNumber = 1;
		int successCount = 0;

		try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzFile));
				TarArchiveInputStream tarInput = new TarArchiveInputStream(gzipInputStream)) {

			TarArchiveEntry entry;

			while ((entry = tarInput.getNextTarEntry()) != null) {

				if (entry.isDirectory()) {
					continue;
				}

				log.info("📄 Processing TAR entry: {}", entry.getName());

				BufferedReader reader = new BufferedReader(new InputStreamReader(tarInput, StandardCharsets.UTF_8));

				String line;

				while ((line = reader.readLine()) != null) {

					if (line.trim().isEmpty())
						continue;

					try {

						JsonNode jsonNode = objectMapper.readTree(line);

						EwayGzJsonStorage entity = new EwayGzJsonStorage();
						entity.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);
						entity.setReturnFileCountPrimarId(returnFileCountPrimaryId);
						entity.setFilePath(gzFile.getAbsolutePath());
						entity.setJsonData(jsonNode);
						entity.setFileNumber(fileNum);
						entity.setSequenceNumber(sequenceNumber++);
						entity.setDt(date);
						entity.setCategory(application);
						entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

						batchList.add(entity);
						successCount++;

						if (batchList.size() == 500) {
							ewayGzJsonStorageRepository.saveAll(batchList);
							batchList.clear();
						}

					} catch (Exception jsonEx) {
						log.error("❌ Invalid JSON inside TAR | entry={} | reason={}", entry.getName(),
								jsonEx.getMessage());
					}
				}
			}

			if (!batchList.isEmpty()) {
				ewayGzJsonStorageRepository.saveAll(batchList);
			}

			long time = System.currentTimeMillis() - start;

			log.info("✔ TAR.GZ Extraction Completed | totalSaved={} | time={}ms", successCount, time);
			// ✅ Store file path for later processing
			DateEwayGzFilePath dateEwayGzFilePath = new DateEwayGzFilePath();
			dateEwayGzFilePath.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);
			dateEwayGzFilePath.setReturnFileCountPrimarId(returnFileCountPrimaryId);
			dateEwayGzFilePath.setFilePath(gzFile.getAbsolutePath());
			dateEwayGzFilePath.setCategory(application);
			dateEwayGzFilePath.setDt(date);
			dateEwayGzFilePath.setIsProcessed(false);
			dateEwayGzFilePath.setSequenceNumber(successCount);
			dateEwayGzFilePathRepository.save(dateEwayGzFilePath);

			log.info("📌 File path stored for later processing: {}", gzFile.getAbsolutePath());

		} catch (Exception e) {

			log.error("🔥 Failed to extract TAR.GZ file={}", gzFile.getAbsolutePath(), e);
			throw e;
		}
	}

}
