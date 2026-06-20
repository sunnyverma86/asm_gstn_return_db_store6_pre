package com.deloitte.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deloitte.returns.entity.EwayGzJsonStorageView;
import com.deloitte.returns.entity.AEwayBill.EwbDetailsData;
import com.deloitte.returns.entity.log.EwbPartaInitialJson;
import com.deloitte.returns.entity.log.EwbPartbInitialJson;
import com.deloitte.returns.repository.eway.EwayGzJsonStorageViewRepository;
import com.deloitte.returns.repository.eway.EwbPartaInitialJsonRepository;
import com.deloitte.returns.repository.eway.EwbPartbInitialJsonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EwayBillApiSupportService {

	@Autowired
	private EwbPartaInitialJsonRepository ewbPartaInitialJsonRepository;

	@Autowired
	private EwbPartbInitialJsonRepository ewbPartbInitialJsonRepository;

	@Autowired
	private EwayGzJsonStorageViewRepository ewayGzJsonStorageViewRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${ewayBill.file.gz.file}")
	private String ewayBillFileGzFile;

	private static final int BATCH_SIZE = 500;

	protected static final int HARD_MAX_RETRY = 5;
	protected static final int IO_BUFFER_SIZE = 8192;
	protected static final int THREAD_POOL_SIZE = 150; // Adjust the number of threads as needed

	//
	public String saveGzFile(ResponseEntity<byte[]> response, EwbDetailsData fileDetail, int fileNum) {

		try {

			String category = fileDetail.getEwbCategory();

			// ✅ Replace / with -
			String formattedDate = fileDetail.getEwbDt().replace("/", "-");

			String baseDir = resolveDirectoryPath(category);
			Path appPath = Paths.get(baseDir);

			// folder: 16-11-2023
			Path dateFolder = appPath.resolve(formattedDate);

			Files.createDirectories(dateFolder);

			// file: 16-11-2023_4_downloaded_file.gz
			String fileName = formattedDate + "_" + fileNum + "_downloaded_file.gz";

			Path gzPath = dateFolder.resolve(fileName);

			log.info("📂 Target GZ Path: {}", gzPath.toAbsolutePath());

			if (Files.exists(gzPath)) {
				log.info("⚠ File already exists: {}", gzPath.getFileName());
				return "File already downloaded.";
			}

			// ✅ Write file
			Files.write(gzPath, response.getBody());

			log.info("✅ GZ File saved successfully at: {}", gzPath.toAbsolutePath());

			extractAndStoreJson(gzPath.toFile(), fileNum, category, formattedDate, fileDetail.getId(),
					fileDetail.getReturnFileCountId(), gzPath.toAbsolutePath());

			return "File downloaded and processed successfully.";

		} catch (Exception e) {

			log.error("❌ Error while downloading file | Reason={}", e.getMessage(), e);
			return "Error while processing file: " + e.getMessage();
		}
	}

	private String resolveDirectoryPath(String application) {

		if (application == null || application.trim().isEmpty()) {
			throw new IllegalArgumentException("Application cannot be null or empty");
		}

		// Normalize application name
		String folderName = application.trim().toUpperCase();

		// Base path from property
		Path basePath = Paths.get(ewayBillFileGzFile);

		// Final path → base + PARTA / PARTB
		Path finalPath = basePath.resolve(folderName);

		return finalPath.toString();
	}

	private void extractAndStoreJson(File gzFile, int fileNum, String application, String date,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimaryId, Path gzPath) throws Exception {

		long startTime = System.currentTimeMillis();

		log.info("▶ GZ Extraction Started | file={} | application={} | fileNum={} | returnFileDetailPrimaryId={}",
				gzFile.getAbsolutePath(), application, fileNum, returnFileDetailPrimaryId);

		final ObjectMapper objectMapper = new ObjectMapper();

		int sequenceNumber = 1;
		int successCount = 0;
		int failedCount = 0;

		final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		List<EwbPartaInitialJson> batchListPartA = new ArrayList<>(BATCH_SIZE);

		List<EwbPartbInitialJson> batchListPartB = new ArrayList<>(BATCH_SIZE);

		List<EwayGzJsonStorageView> batchListView = new ArrayList<>(BATCH_SIZE);

		try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzFile));

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8), 65536)) {

			String line;

			while ((line = reader.readLine()) != null) {

				if (line.isBlank()) {
					continue;
				}

				try {

					JsonNode jsonNode = objectMapper.readTree(line);

					/*
					 * ========================= PART A PROCESSING =========================
					 */
					if ("PARTA".equalsIgnoreCase(application)) {

						EwbPartaInitialJson entityPartA = new EwbPartaInitialJson();

						entityPartA.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);

						entityPartA.setReturnFileCountPrimaryId(returnFileCountPrimaryId);

						entityPartA.setFilePath(gzPath.toString());

						entityPartA.setJsonData(jsonNode);

						entityPartA.setFileNumber(fileNum);

						entityPartA.setSequenceNumber(sequenceNumber);

						entityPartA.setDt(date);

						entityPartA.setCategory(application);

						entityPartA.setIsProcessed(false);

						entityPartA.setInsertDt(currentTimestamp);

						batchListPartA.add(entityPartA);
					}

					/*
					 * ========================= PART B PROCESSING =========================
					 */
					else if ("PARTB".equalsIgnoreCase(application)) {

						EwbPartbInitialJson entityPartB = new EwbPartbInitialJson();

						entityPartB.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);

						entityPartB.setReturnFileCountPrimaryId(returnFileCountPrimaryId);

						entityPartB.setFilePath(gzPath.toString());

						entityPartB.setJsonData(jsonNode);

						entityPartB.setFileNumber(fileNum);

						entityPartB.setSequenceNumber(sequenceNumber);

						entityPartB.setDt(date);

						entityPartB.setCategory(application);

						entityPartB.setIsProcessed(false);

						entityPartB.setInsertDt(currentTimestamp);

						batchListPartB.add(entityPartB);
					}

					/*
					 * ========================= INVALID APPLICATION =========================
					 */
					else {

						log.error("❌ Invalid application type found | application={} | file={}", application,
								gzFile.getName());

						throw new IllegalArgumentException("Invalid application type : " + application);
					}

					/*
					 * ========================= COMMON VIEW TABLE =========================
					 */
					EwayGzJsonStorageView entityView = new EwayGzJsonStorageView();

					entityView.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);

					entityView.setReturnFileCountPrimaryId(returnFileCountPrimaryId);

					entityView.setFilePath(gzPath.toString());

					entityView.setFilePathAbs(gzPath.toAbsolutePath().toString());

					entityView.setFileNumber(fileNum);

					entityView.setSequenceNumber(sequenceNumber);

					entityView.setDt(date);

					entityView.setCategory(application);

					entityView.setIsProcessed(false);

					entityView.setInsertDt(currentTimestamp);

					batchListView.add(entityView);

					successCount++;

					/*
					 * ========================= BATCH INSERT =========================
					 */
					if (batchListView.size() >= BATCH_SIZE) {

						saveBatchData(batchListPartA, batchListPartB, batchListView);

						log.info("✔ Batch Insert Completed | batchSize={} | totalSuccessCount={} | file={}", BATCH_SIZE,
								successCount, gzFile.getName());
					}

					sequenceNumber++;

				} catch (Exception jsonEx) {

					failedCount++;

					log.error("❌ Failed To Process JSON Line | sequenceNumber={} | file={} | reason={}", sequenceNumber,
							gzFile.getName(), jsonEx.getMessage(), jsonEx);
				}
			}

			/*
			 * ========================= FINAL BATCH SAVE =========================
			 */
			saveBatchData(batchListPartA, batchListPartB, batchListView);

			long totalTime = System.currentTimeMillis() - startTime;

			log.info(
					"✔ GZ Extraction Completed | file={} | application={} | totalSuccess={} | totalFailed={} | totalTime={} ms",
					gzFile.getName(), application, successCount, failedCount, totalTime);

		} catch (Exception e) {

			log.error("🔥 GZ Extraction Failed | file={} | application={} | reason={}", gzFile.getAbsolutePath(),
					application, e.getMessage(), e);

			throw e;
		}
	}

	private void saveBatchData(List<EwbPartaInitialJson> batchListPartA, List<EwbPartbInitialJson> batchListPartB,
			List<EwayGzJsonStorageView> batchListView) {

		try {

			if (!batchListPartA.isEmpty()) {

				ewbPartaInitialJsonRepository.saveAll(batchListPartA);

				ewbPartaInitialJsonRepository.flush();

				batchListPartA.clear();
			}

			if (!batchListPartB.isEmpty()) {

				ewbPartbInitialJsonRepository.saveAll(batchListPartB);

				ewbPartbInitialJsonRepository.flush();

				batchListPartB.clear();
			}

			if (!batchListView.isEmpty()) {

				ewayGzJsonStorageViewRepository.saveAll(batchListView);

				ewayGzJsonStorageViewRepository.flush();

				batchListView.clear();
			}

			entityManager.clear();

		} catch (Exception e) {

			log.error("🔥 Batch Insert Failed | reason={}", e.getMessage(), e);

			throw e;
		}
	}

}
