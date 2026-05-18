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
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.common.bean.Result;
import com.deloitte.common.entity.DateReturnGzFilePath;
import com.deloitte.returns.entity.BaseJsonEntity;
import com.deloitte.returns.entity.Cmp08InitialJson;
import com.deloitte.returns.entity.Itc02InitialJson;
import com.deloitte.returns.entity.PaymentInitialJson;
import com.deloitte.returns.entity.R10InitialJson;
import com.deloitte.returns.entity.R11InitialJson;
import com.deloitte.returns.entity.R1InitialJson;
import com.deloitte.returns.entity.R1aInitialJson;
import com.deloitte.returns.entity.R2bInitialJson;
import com.deloitte.returns.entity.R3bInitialJson;
import com.deloitte.returns.entity.R4InitialJson;
import com.deloitte.returns.entity.R5InitialJson;
import com.deloitte.returns.entity.R6InitialJson;
import com.deloitte.returns.entity.R7InitialJson;
import com.deloitte.returns.entity.R8InitialJson;
import com.deloitte.returns.entity.R98aInitialJson;
import com.deloitte.returns.entity.R9InitialJson;
import com.deloitte.returns.entity.R9aInitialJson;
import com.deloitte.returns.entity.R9cInitialJson;
import com.deloitte.returns.entity.ReturnGzJsonStorage;
import com.deloitte.returns.entity.Cmp8.Cmp8;
import com.deloitte.returns.entity.Gstr1.Gstr1;
import com.deloitte.returns.entity.Gstr10.Gstr10;
import com.deloitte.returns.entity.Gstr11.Gstr11;
import com.deloitte.returns.entity.Gstr1A.Gstr1A;
import com.deloitte.returns.entity.Gstr2b.Gstr2b;
import com.deloitte.returns.entity.Gstr3b.Gstr3b;
import com.deloitte.returns.entity.Gstr4.Gstr4;
import com.deloitte.returns.entity.Gstr5.Gstr5;
import com.deloitte.returns.entity.Gstr7.Gstr7;
import com.deloitte.returns.entity.Gstr8.Gstr8;
import com.deloitte.returns.entity.Gstr9.Gstr9;
import com.deloitte.returns.entity.Gstr98a.Gstr98a;
import com.deloitte.returns.entity.Gstr9a.Gstr9A;
import com.deloitte.returns.entity.Gstr9c.Gstr9c;
import com.deloitte.returns.entity.Itc2.Itc2;
import com.deloitte.returns.entity.Payment.Payment;
import com.deloitte.service.abs.AbstractFileDownloadHelperCommon;
import com.deloitte.service.utility.SftpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class FileDownloadHelperCommon extends AbstractFileDownloadHelperCommon {

	public Result downloadAndProcessFileWithOutSaveInDb(String fileUrl, String date, String fileNumValue,
			String application, Long returnFilecountId, Long returnFileDetailId) {

		long start = System.currentTimeMillis();

		log.info("▶ START | app={} | date={} | file={} | url={}", application, date, fileNumValue, fileUrl);

		try {

			// 1️⃣ Validate input
			if (fileUrl == null || fileUrl.isBlank()) {
				return new Result(null, "Invalid file URL.");
			}

			if (fileNumValue == null || fileNumValue.isBlank()) {
				return new Result(null, "Invalid file number.");
			}

			int fileNum;
			try {
				fileNum = Integer.parseInt(fileNumValue);
			} catch (NumberFormatException e) {
				return new Result(null, "File number must be numeric.");
			}

			// 2️⃣ Correct method call (Proper order)
			String message = downloadAndStoreGzFile(fileUrl, fileNum, application, date, returnFileDetailId,
					returnFilecountId);

			long time = System.currentTimeMillis() - start;

			if (message != null && message.toLowerCase().contains("success")) {

				log.info("✔ SUCCESS | app={} | date={} | file={} | time={}ms | msg={}", application, date, fileNumValue,
						time, message);

				return new Result("SUCCESS", message);

			} else {

				log.warn("⚠ PROCESS COMPLETED WITH MESSAGE | app={} | date={} | file={} | time={}ms | msg={}",
						application, date, fileNumValue, time, message);

				return new Result(null, message);
			}

		} catch (Exception ex) {

			long time = System.currentTimeMillis() - start;

			log.error("🔥 CRITICAL FAILURE | app={} | date={} | file={} | time={}ms", application, date, fileNumValue,
					time, ex);

			return new Result(null, "Error: " + ex.getMessage());
		}
	}

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

			log.info("Uploaded to Linux server successfully");

			// Step 5: KEEP YOUR EXISTING METHOD

			//

			log.info("✅ GZ File saved successfully at: {}", gzPath.toAbsolutePath());
			// Step 4: Upload to Linux server
			File downloadedGzFile = gzPath.toFile();
			String remoteFolder = getRemoteFolder(application);

			String serverFilePath = SftpUtil.uploadFile(downloadedGzFile, remoteFolder, date);

			// 5️⃣ Extract and store JSON into DB
			extractAndStoreJson(gzPath.toFile(), fileNum, application, date, returnFileDetailPrimaryId,
					returnFileCountPrimaryId, serverFilePath);

			return "File downloaded and processed successfully.";

		} catch (Exception e) {
			log.error("❌ Error while downloading file | URL={} | Reason={}", fileUrl, e.getMessage(), e);
			return "Error while processing file: " + e.getMessage();
		}
	}

	// ========================= DIRECTORY RESOLVER =========================

	protected String resolveDirectoryPath(String application) {
		// log.info("▶ Resolving directory path for application: {}", application);

		if (application == null || application.trim().isEmpty()) {
			// log.warn("⚠ Application is null or empty. Using default CMP08 directory
			// path.");
			return cmp8FileLocation;
		}

		switch (application.toUpperCase()) {

		case "CMP08":
			return cmp8FileLocation;

		case "GSTR1":
			return gstr1FileLocation;

		case "GSTR1A":
			return gstr1AFileLocation;

		case "GSTR2B":
			return gstr2bFileLocation;

		case "GSTR3B":
			return gstr3bFileLocation;

		case "GSTR4":
			return gstr4FileLocation;

		case "GSTR5":
			return gstr5FileLocation;

		case "GSTR6":
			return gstr6FileLocation;

		case "GSTR7":
			return gstr7FileLocation;

		case "GSTR8":
			return gstr8FileLocation;

		case "GSTR9":
			return gstr9FileLocation;

		case "GSTR9C":
			return gstr9cFileLocation;

		case "GSTR9A":
			return gstr9aFileLocation;

		case "GSTR98A":
			return gstr98aFileLocation;

		case "GSTR10":
			return gstr10FileLocation;

		case "GSTR11":
			return gstr11FileLocation;

		case "ITC02":
			return itc2FileLocation;

		case "PAYMENT":
			return paymentFileLocation;

		case "EWAYBILL":
			return ewayBillFileLocation;

		case "EWAYBILL-EXTRACTED":
			return ewayBillExtractedFileLocation;

		case "CM8":
			return cmp8FileLocation;

		case "R1":
			return gstr1FileLocation;

		case "R1A":
			return gstr1AFileLocation;

		case "R2B":
			return gstr2bFileLocation;

		case "R3B":
			return gstr3bFileLocation;

		case "R4":
			return gstr4FileLocation;

		case "R5":
			return gstr5FileLocation;

		case "R6":
			return gstr6FileLocation;

		case "R7":
			return gstr7FileLocation;

		case "R8":
			return gstr8FileLocation;

		case "R9":
			return gstr9FileLocation;

		case "R9C":
			return gstr9cFileLocation;

		case "R9A":
			return gstr9aFileLocation;

		case "R98A":
			return gstr98aFileLocation;

		case "R10":
			return gstr10FileLocation;

		case "R11":
			return gstr11FileLocation;

		default:
			// log.warn("⚠ Unknown application: {} | using default CMP08 path",
			// application);
			return cmp8FileLocation;
		}
	}

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

	private void extractAndStoreJson(File gzFile, int fileNum, String application, String date,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimaryId, String serverFilePath) throws Exception {

		long startTime = System.currentTimeMillis();

		log.info("TAR.GZ extraction started | file={} | application={}", gzFile.getAbsolutePath(), application);

		ObjectMapper objectMapper = new ObjectMapper();
		List<BaseJsonEntity> batchList = new ArrayList<>(500);

		int sequenceNumber = 1;
		int successCount = 0;
		int failedCount = 0;

		Date parsedDate = Date.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")));

		try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzFile));

				TarArchiveInputStream tarInput = new TarArchiveInputStream(gzipInputStream)) {

			TarArchiveEntry entry;

			while ((entry = tarInput.getNextTarEntry()) != null) {

				if (entry.isDirectory()) {
					continue;
				}

				log.info("Processing TAR entry={}", entry.getName());

				BufferedReader reader = new BufferedReader(new InputStreamReader(tarInput, StandardCharsets.UTF_8));

				String line;

				while ((line = reader.readLine()) != null) {

					if (line.trim().isEmpty()) {
						continue;
					}

					try {
						JsonNode jsonNode = objectMapper.readTree(line);

						BaseJsonEntity entity = commonServiceGstrImplSupport.getEntityByApplication(application);

						entity.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);
						entity.setReturnFileCountPrimaryId(returnFileCountPrimaryId);
						entity.setFilePath(serverFilePath);
						entity.setJsonData(jsonNode);
						entity.setFileNumber(fileNum);
						entity.setSequenceNumber(sequenceNumber++);
						entity.setDt(parsedDate);
						entity.setCategory(application);
						entity.setInsertDt(new Timestamp(System.currentTimeMillis()));

						batchList.add(entity);
						successCount++;

						if (batchList.size() >= 500) {
							saveBatchByApplication(application, batchList);
							batchList.clear();
						}

					} catch (Exception ex) {
						failedCount++;

						log.error("Invalid JSON found | file={} | entry={} | error={}", gzFile.getName(),
								entry.getName(), ex.getMessage());
					}
				}
			}

			if (!batchList.isEmpty()) {
				saveBatchByApplication(application, batchList);
			}

			storeFilePathDetails(gzFile, application, date, successCount, returnFileDetailPrimaryId,
					returnFileCountPrimaryId);

			log.info("TAR.GZ extraction completed | file={} | success={} | failed={} | duration={} ms",
					gzFile.getName(), successCount, failedCount, (System.currentTimeMillis() - startTime));

		} catch (Exception e) {

			log.error("TAR.GZ extraction failed | file={} | application={}", gzFile.getAbsolutePath(), application, e);

			throw e;
		}
	}

	public void saveBatchByApplication(String application, List<BaseJsonEntity> batchList) {

		if (batchList == null || batchList.isEmpty()) {
			return;
		}

		log.info("Saving batch size={} for application={}", batchList.size(), application);

		switch (application.toUpperCase()) {

		case "GSTR1":
			r1Repo.saveAll(batchList.stream().map(e -> (R1InitialJson) e).toList());
			break;

		case "GSTR1A":
			r1aRepo.saveAll(batchList.stream().map(e -> (R1aInitialJson) e).toList());
			break;

		case "GSTR2B":
			r2bRepo.saveAll(batchList.stream().map(e -> (R2bInitialJson) e).toList());
			break;

		case "GSTR3B":
			r3bRepo.saveAll(batchList.stream().map(e -> (R3bInitialJson) e).toList());
			break;

		case "GSTR4":
			r4Repo.saveAll(batchList.stream().map(e -> (R4InitialJson) e).toList());
			break;

		case "GSTR5":
			r5Repo.saveAll(batchList.stream().map(e -> (R5InitialJson) e).toList());
			break;

		case "GSTR6":
			r6Repo.saveAll(batchList.stream().map(e -> (R6InitialJson) e).toList());
			break;

		case "GSTR7":
			r7Repo.saveAll(batchList.stream().map(e -> (R7InitialJson) e).toList());
			break;

		case "GSTR8":
			r8Repo.saveAll(batchList.stream().map(e -> (R8InitialJson) e).toList());
			break;

		case "GSTR9":
			r9Repo.saveAll(batchList.stream().map(e -> (R9InitialJson) e).toList());
			break;

		case "GSTR10":
			r10Repo.saveAll(batchList.stream().map(e -> (R10InitialJson) e).toList());
			break;

		case "GSTR11":
			r11Repo.saveAll(batchList.stream().map(e -> (R11InitialJson) e).toList());
			break;

		case "GSTR9A":
			r9aRepo.saveAll(batchList.stream().map(e -> (R9aInitialJson) e).toList());
			break;

		case "GSTR98A":
			r98aRepo.saveAll(batchList.stream().map(e -> (R98aInitialJson) e).toList());
			break;

		case "GSTR9C":
			r9cRepo.saveAll(batchList.stream().map(e -> (R9cInitialJson) e).toList());
			break;

		case "CMP08":
			cmp08Repo.saveAll(batchList.stream().map(e -> (Cmp08InitialJson) e).toList());
			break;

		case "PAYMENT":
			paymentRepo.saveAll(batchList.stream().map(e -> (PaymentInitialJson) e).toList());
			break;

		case "ITC02":
			itc02Repo.saveAll(batchList.stream().map(e -> (Itc02InitialJson) e).toList());
			break;

		case "R1":
			r1Repo.saveAll(batchList.stream().map(e -> (R1InitialJson) e).toList());
			break;

		case "R1A":
			r1aRepo.saveAll(batchList.stream().map(e -> (R1aInitialJson) e).toList());
			break;

		case "R2B":
			r2bRepo.saveAll(batchList.stream().map(e -> (R2bInitialJson) e).toList());
			break;

		case "R3B":
			r3bRepo.saveAll(batchList.stream().map(e -> (R3bInitialJson) e).toList());
			break;

		case "R4":
			r4Repo.saveAll(batchList.stream().map(e -> (R4InitialJson) e).toList());
			break;

		case "R5":
			r5Repo.saveAll(batchList.stream().map(e -> (R5InitialJson) e).toList());
			break;

		case "R6":
			r6Repo.saveAll(batchList.stream().map(e -> (R6InitialJson) e).toList());
			break;

		case "R7":
			r7Repo.saveAll(batchList.stream().map(e -> (R7InitialJson) e).toList());
			break;

		case "R8":
			r8Repo.saveAll(batchList.stream().map(e -> (R8InitialJson) e).toList());
			break;

		case "R9":
			r9Repo.saveAll(batchList.stream().map(e -> (R9InitialJson) e).toList());
			break;

		case "R10":
			r10Repo.saveAll(batchList.stream().map(e -> (R10InitialJson) e).toList());
			break;

		case "R11":
			r11Repo.saveAll(batchList.stream().map(e -> (R11InitialJson) e).toList());
			break;

		case "R9A":
			r9aRepo.saveAll(batchList.stream().map(e -> (R9aInitialJson) e).toList());
			break;

		case "R98A":
			r98aRepo.saveAll(batchList.stream().map(e -> (R98aInitialJson) e).toList());
			break;

		case "R9C":
			r9cRepo.saveAll(batchList.stream().map(e -> (R9cInitialJson) e).toList());
			break;

		case "CM8":
			cmp08Repo.saveAll(batchList.stream().map(e -> (Cmp08InitialJson) e).toList());
			break;

		case "payment":
			paymentRepo.saveAll(batchList.stream().map(e -> (PaymentInitialJson) e).toList());
			break;

		default:
			returnGzJsonStorageRepository.saveAll(batchList.stream().map(e -> (ReturnGzJsonStorage) e).toList());
		}

		log.info("Successfully saved batch for application={}", application);
	}

	private void storeFilePathDetails(File gzFile, String application, String date, int successCount,
			Long returnFileDetailPrimaryId, Long returnFileCountPrimaryId) {

		DateReturnGzFilePath entity = new DateReturnGzFilePath();

		entity.setReturnFileDetailPrimaryId(returnFileDetailPrimaryId);
		entity.setReturnFileCountPrimaryId(returnFileCountPrimaryId);
		entity.setFilePath(gzFile.getAbsolutePath());
		entity.setApplication(application);
		entity.setDt(date);
		entity.setIsProcessed(false);
		entity.setSequenceNumber(successCount);

		dateReturnGzFilePathRepository.save(entity);

		log.info("File metadata stored successfully | file={}", gzFile.getName());
	}
	// ====================SAVE FUNCTIONALITY=============================//

	public long saveDataFromJsonToDbOld(String application) {

		long start = System.currentTimeMillis();
		log.info("▶ START JSON ingestion | app={}", application);

		int attempt = 1;

		while (attempt <= HARD_MAX_RETRY) {
			List<DateReturnGzFilePath> list = dateReturnGzFilePathRepository
					.findTop100ByApplicationAndIsProcessedFalseOrderByIdAsc(application);

			if (list.isEmpty())
				break;

			log.info("🔁 Attempt {}/{} | Pending Files={}", attempt, HARD_MAX_RETRY, list.size());

			ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

			for (int i = 0; i < list.size(); i += BATCH_SIZE) {

				int s = i;
				int e = Math.min(i + BATCH_SIZE, list.size());

				pool.submit(() -> processBatch(list, s, e, application));
			}

			shutdown(pool);

			// long pending = commonFileDetailsRepository.countByIsProcessedFalse();
			long pending = dateReturnGzFilePathRepository.countByApplicationAndIsProcessedFalse(application);

			log.info("✔ Attempt {} completed | Remaining={}", attempt, pending);

			if (pending <= 1)
				break;

			attempt++;
		}

		long time = System.currentTimeMillis() - start;

		log.info("🏁 END JSON ingestion | time={}ms", time);

		return dateReturnGzFilePathRepository.countByApplicationAndIsProcessedFalse(application);
	}

	private void processBatch(List<DateReturnGzFilePath> list, int start, int end, String application) {

		ObjectMapper mapper = new ObjectMapper(); // can be singleton

		for (int i = start; i < end; i++) {

			DateReturnGzFilePath file = list.get(i);

			try {

				boolean status = processFileByApplication(application, file.getFilePath(), mapper);

				file.setIsProcessed(status);
				dateReturnGzFilePathRepository.save(file);

			} catch (Exception ex) {

				log.error("❌ File failed → {}", file.getFilePath(), ex);
			}
		}
	}

	private boolean processFileByApplication(String application, String gzFilePath, ObjectMapper mapper)
			throws Exception {

		File file = new File(gzFilePath);

		boolean isSaved = false;

		try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));

				TarArchiveInputStream tarInput = new TarArchiveInputStream(gzipInputStream)) {

			TarArchiveEntry entry;

			while ((entry = tarInput.getNextTarEntry()) != null) {

				if (entry.isDirectory())
					continue;

				log.info("📂 Processing entry: {}", entry.getName());

				BufferedReader reader = new BufferedReader(new InputStreamReader(tarInput, StandardCharsets.UTF_8));

				String line;

				while ((line = reader.readLine()) != null) {

					if (line.trim().isEmpty())
						continue;

					try {

						switch (application.toUpperCase()) {

						case "GSTR1":
						case "R1":
							gstr1Repository.save(mapper.readValue(line, Gstr1.class));
							isSaved = true;
							break;

						case "GSTR1A":
						case "R1A":
							gstr1ARepository.save(mapper.readValue(line, Gstr1A.class));
							isSaved = true;
							break;

						case "GSTR2B":
						case "RB":
							gstr2bRepository.save(mapper.readValue(line, Gstr2b.class));
							isSaved = true;
							break;

						case "GSTR3B":
						case "R3B":
							gstr3bRepository.save(mapper.readValue(line, Gstr3b.class));
							isSaved = true;
							break;

						case "GSTR4":
						case "R4":
							gstr4Repository.save(mapper.readValue(line, Gstr4.class));
							isSaved = true;
							break;

						case "GSTR5":
						case "R5":
							gstr5Repository.save(mapper.readValue(line, Gstr5.class));
							isSaved = true;
							break;

//						case "GSTR6":
//						case "R6":
//							gstr6Repository.save(mapper.readValue(line, Gstr6.class));
//							isSaved = true;
//							break;

						case "GSTR7":
						case "R7":
							gstr7Repository.save(mapper.readValue(line, Gstr7.class));
							isSaved = true;
							break;

						case "GSTR8":
						case "R8":
							gstr8Repository.save(mapper.readValue(line, Gstr8.class));
							isSaved = true;
							break;

						case "GSTR9":
						case "R9":
							gstr9Repository.save(mapper.readValue(line, Gstr9.class));
							isSaved = true;
							break;

						case "GSTR9A":
						case "R9A":
							gstr9aRepository.save(mapper.readValue(line, Gstr9A.class));
							isSaved = true;
							break;

						case "GSTR98A":
						case "R98A":
							gstr98aRepository.save(mapper.readValue(line, Gstr98a.class));
							isSaved = true;
							break;

						case "GSTR9C":
						case "R9C":
							gstr9cRepository.save(mapper.readValue(line, Gstr9c.class));
							isSaved = true;
							break;

						case "GSTR10":
						case "R10":
							gstr10Repository.save(mapper.readValue(line, Gstr10.class));
							isSaved = true;
							break;

						case "GSTR11":
						case "R11":
							gstr11Repository.save(mapper.readValue(line, Gstr11.class));
							isSaved = true;
							break;

						case "CM908":
						case "CM8":
							cmp8Repository.save(mapper.readValue(line, Cmp8.class));
							isSaved = true;
							break;

						case "ITC02":
							itc2Repository.save(mapper.readValue(line, Itc2.class));
							isSaved = true;
							break;

						case "PAYMENT":
							paymentRepository.save(mapper.readValue(line, Payment.class));
							isSaved = true;
							break;

						default:
							log.error("❌ Unsupported application: {}", application);
							return false;
						}

					} catch (Exception ex) {
						log.error("❌ JSON parse/save failed | line={}", line, ex);
					}
				}
			}
		}

		return isSaved;
	}

	private void shutdown(ExecutorService pool) {

		pool.shutdown();

		try {
			if (!pool.awaitTermination(1, TimeUnit.HOURS)) {
				pool.shutdownNow();
			}
		} catch (InterruptedException e) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	private String getRemoteFolder(String application) {

		if (application == null) {
			return "cmp08";
		}

		switch (application.toUpperCase()) {
		
		case "R1":
			return "R1_Deloitte";

		case "R1A":
			return "R1A_Deloitte";

		case "R2B":
			return "R2B_Deloitte";

		case "R3B":
			return "R3B_Deloitte";

		case "R4":
			return "R4_Deloitte";

		case "R5":
			return "R5_Deloitte";

		case "R6":
			return "R6_Deloitte";

		case "R7":
			return "R7_Deloitte";

		case "R8":
			return "R8_Deloitte";

		case "R9":
			return "R9_Deloitte";

		case "R9A":
			return "R9A_Deloitte";

		case "R9C":
			return "R9C_Deloitte";

		case "R98A":
			return "R98A_Deloitte";

		case "R10":
			return "R10_Deloitte";

		case "R11":
			return "R11_Deloitte";

		case "CM8":
			return "CM8_Deloitte";

		case "ITC02":
			return "ITC02_Deloitte";

		case "PAYMENT":
			return "PAYMENT_Deloitte";

		case "EWAYBILL":
			return "ewaybill";

		case "GSTR1":
			return "R1_Deloitte";

		case "GSTR1A":
			return "R1A_Deloitte";

		case "GSTR2B":
			return "R2B_Deloitte";

		case "GSTR3B":
			return "R3B_Deloitte";

		case "GSTR4":
			return "R4_Deloitte";

		case "GSTR5":
			return "R5_Deloitte";

		case "GSTR6":
			return "R6_Deloitte";

		case "GSTR7":
			return "R7_Deloitte";

		case "GSTR8":
			return "R8_Deloitte";

		case "GSTR9":
			return "R9_Deloitte";

		case "GSTR9A":
			return "R9A_Deloitte";

		case "GSTR9C":
			return "R9C_Deloitte";

		case "GSTR98A":
			return "R98A_Deloitte";

		case "GSTR10":
			return "R10_Deloitte";

		case "GSTR11":
			return "R11_Deloitte";

		case "CMP08":
			return "CM8_Deloitte";

		default:
			return "others";
		}
	}

//	public boolean isDataAlreadyPresent(String application, Date date) {
//
//		switch (application.toUpperCase()) {
//
//		case "GSTR1":
//			return r1Repo.existsByDt(date);
//
//		case "GSTR1A":
//			return r1aRepo.existsByDt(date);
//
//		case "GSTR2B":
//			return r2bRepo.existsByDt(date);
//
//		case "GSTR3B":
//			return r3bRepo.existsByDt(date);
//
//		case "GSTR4":
//			return r4Repo.existsByDt(date);
//
//		case "GSTR5":
//			return r5Repo.existsByDt(date);
//
//		case "GSTR6":
//			return r6Repo.existsByDt(date);
//
//		case "GSTR7":
//			return r7Repo.existsByDt(date);
//
//		case "GSTR8":
//			return r8Repo.existsByDt(date);
//
//		case "GSTR9":
//			return r9Repo.existsByDt(date);
//
//		case "GSTR10":
//			return r10Repo.existsByDt(date);
//
//		case "GSTR11":
//			return r11Repo.existsByDt(date);
//
//		case "GSTR9A":
//			return r9aRepo.existsByDt(date);
//
//		case "GSTR98A":
//			return r98aRepo.existsByDt(date);
//
//		case "GSTR9C":
//			return r9cRepo.existsByDt(date);
//
//		case "CMP08":
//			return cmp08Repo.existsByDt(date);
//			
//		case "R1":
//			return r1Repo.existsByDt(date);
//
//		case "R1A":
//			return r1aRepo.existsByDt(date);
//
//		case "R2B":
//			return r2bRepo.existsByDt(date);
//
//		case "R3B":
//			return r3bRepo.existsByDt(date);
//
//		case "R4":
//			return r4Repo.existsByDt(date);
//
//		case "R5":
//			return r5Repo.existsByDt(date);
//
//		case "R6":
//			return r6Repo.existsByDt(date);
//
//		case "R7":
//			return r7Repo.existsByDt(date);
//
//		case "R8":
//			return r8Repo.existsByDt(date);
//
//		case "R9":
//			return r9Repo.existsByDt(date);
//
//		case "R10":
//			return r10Repo.existsByDt(date);
//
//		case "R11":
//			return r11Repo.existsByDt(date);
//
//		case "R9A":
//			return r9aRepo.existsByDt(date);
//
//		case "R98A":
//			return r98aRepo.existsByDt(date);
//
//		case "R9C":
//			return r9cRepo.existsByDt(date);
//
//		case "CM8":
//			return cmp08Repo.existsByDt(date);
//
//		case "payment":
//			return paymentRepo.existsByDt(date);
//
//		case "itc02":
//			return itc02Repo.existsByDt(date);
//
//		default:
//			return itc02Repo.existsByDt(date);
//		}
//	}
///////////////////////////////////////////////
	@Transactional
	public long saveDataFromJsonToDb(String application) {

		long start = System.currentTimeMillis();
		log.info("▶ START DB JSON → ENTITY ingestion | app={}", application);

		int totalProcessed = 0;

		while (true) {

			List<? extends BaseJsonEntity> list = fetchTop30(application);

			if (list == null || list.isEmpty()) {
				log.info("✅ No more records found for app={}", application);
				break;
			}

			log.info("📦 Processing batch size={}", list.size());

			for (BaseJsonEntity record : list) {
				try {
					processJsonRecord(application, record);
					// record.setIsProcess(true);
					totalProcessed++;

				} catch (Exception e) {
					// log.error("❌ Failed for record id={}", record.getId(), e);
				}
			}

			// saveBatch(application, list);

			log.info("✔ Batch processed | totalProcessed={}", totalProcessed);
		}

		long time = System.currentTimeMillis() - start;
		log.info("🏁 END DB JSON ingestion | time={} ms", time);

		return totalProcessed;
	}

	private List<? extends BaseJsonEntity> fetchTop30(String application) {

		switch (application.toLowerCase()) {

		case "gstr1":
			// return r1Repo.findByTop30IsProcessIsFalseOrderByIdDesc();

		case "gstr11":
			// return r11Repo.findByTop30IsProcessIsFalseOrderByIdDesc();

		case "gstr3b":
			// return r3bRepo.findByTop30IsProcessIsFalseOrderByIdDesc();

			// add others...

		default:
			throw new IllegalArgumentException("Unsupported application: " + application);
		}
	}

	private void processJsonRecord(String application, BaseJsonEntity record) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = null;
//
//	    if (jsonNode == null || jsonNode.isEmpty()) {
//	       // log.warn("⚠ Empty JSON for id={}", record.getId());
//	        return;
//	    }

		switch (application.toLowerCase()) {

		case "gstr1":
			Gstr1 gstr1 = mapper.treeToValue(jsonNode, Gstr1.class);
			// gstr1.setReturnFileDetailPrimaryId(record.getReturnFileDetailPrimaryId());
			gstr1Repository.save(gstr1);
			break;

		case "gstr11":
			Gstr11 gstr11 = mapper.treeToValue(jsonNode, Gstr11.class);
			gstr11Repository.save(gstr11);
			break;

		case "gstr3b":
			Gstr3b gstr3b = mapper.treeToValue(jsonNode, Gstr3b.class);
			gstr3bRepository.save(gstr3b);
			break;

		default:
			throw new IllegalArgumentException("Unsupported application: " + application);
		}
	}

}
