package com.deloitte.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.service.impl.RegistrationServiceImpl;
import com.deloitte.service.support.CommonServiceGstrUtilityImpl;
import com.deloitte.service.support.GstinServiceRegistration;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/common/gstr")
@Log4j2
public class ReturnController {

	@Autowired
	private CommonServiceGstrUtilityImpl commonControllerGstrUtilityImpl;

	@Autowired
	private GstinServiceRegistration gstinServiceRegistration;

	@Autowired
	protected GstUserSessionServices gstUserSessionServices;

	@Autowired
	protected RegistrationServiceImpl registrationServiceImpl;

	private static final String USERNAME = "GSTG2G18";

	private ResponseEntity<String> scheduleWithLogging(String application, String apiName) {

		long startTime = System.currentTimeMillis();
		log.info("🚀 [START] API={} | Application={}", apiName, application);

		try {

			if (gstUserSessionServices.isSessionExpired(USERNAME)) {

				log.error("❌ SESSION EXPIRED BEFORE 6 HOUR");

				return ResponseEntity.badRequest().body("SESSION EXPIRED BEFORE 6 HOUR");
			}

			// Step 1: Schedule Download
			log.info("📥 [STEP-1] Starting schedule download | Application={}", application);
			String response = commonControllerGstrUtilityImpl.scheduleDownload(application);
			log.info("✅ [STEP-1] Schedule download completed | Response={}", response);

			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			long timeTaken = System.currentTimeMillis() - startTime;

			log.error("❌ [ERROR] API={} | Application={} | TimeTaken={} ms | Message={}", apiName, application,
					timeTaken, ex.getMessage(), ex);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to process request for application=" + application);
		}
	}

	// ========================== 1️⃣ DOWNLOAD APIs ==========================

	@Scheduled(cron = "0 10 4 * * *")
	@GetMapping("/scheduleCmp08") // ready
	public ResponseEntity<String> scheduleCmp08Download() {
		return scheduleWithLogging("CM8", "scheduleCmp08Download");
	}

	@GetMapping("/scheduleItc02") // no need
	public ResponseEntity<String> scheduleItc02Download() {
		return scheduleWithLogging("ITC02", "scheduleItc02Download");
	}

	@Scheduled(cron = "0 30 4 * * *")
	@GetMapping("/schedulePayment") // ready
	public ResponseEntity<String> schedulePaymentDownload() {
		return scheduleWithLogging("payment", "schedulePaymentDownload");
	}

	@GetMapping("/schedule-recon-data-download")
	public ResponseEntity<String> scheduleReconDataDownload() {
		return scheduleWithLogging("recon", "scheduleReconDataDownload");
	}

	@Scheduled(cron = "0 10 5 * * *")
	@GetMapping("/scheduleGstr1") // ready
	public ResponseEntity<String> scheduleGstr1Download() {
		return scheduleWithLogging("R1", "scheduleGstr1Download");
	}

	@Scheduled(cron = "0 50 5 * * *")
	@GetMapping("/scheduleGstr1a") // no need
	public ResponseEntity<String> scheduleGstr1aDownload() {
		return scheduleWithLogging("R1A", "scheduleGstr1aDownload");
	}

	@Scheduled(cron = "0 10 6 * * *")
	@GetMapping("/scheduleGstr2b") // ready
	public ResponseEntity<String> scheduleGstr2bDownload() {
		return scheduleWithLogging("R2B", "scheduleGstr2bDownload");
	}

	@Scheduled(cron = "0 40 6 * * *")
	@GetMapping("/scheduleGstr3b") // ready
	public ResponseEntity<String> scheduleGstr3bDownload() {
		return scheduleWithLogging("R3B", "scheduleGstr3bDownload");
	}

	@Scheduled(cron = "0 45 7 * * *")
	@GetMapping("/scheduleGstr4") // ready
	public ResponseEntity<String> scheduleGstr4Download() {
		return scheduleWithLogging("R4", "scheduleGstr4Download");
	}

	@GetMapping("/scheduleGstr5") // ready not working
	public ResponseEntity<String> scheduleGstr5Download() {
		return scheduleWithLogging("R5", "scheduleGstr5Download");
	}

	@Scheduled(cron = "0 55 7 * * *")
	@GetMapping("/scheduleGstr6") // ready
	public ResponseEntity<String> scheduleGstr6Download() {
		return scheduleWithLogging("R6", "scheduleGstr6Download"); // Completed
	}

	@Scheduled(cron = "0 10 8 * * *")
	@GetMapping("/scheduleGstr7") // ready--
	public ResponseEntity<String> scheduleGstr7Download() {
		return scheduleWithLogging("R7", "scheduleGstr7Download");
	}

	@Scheduled(cron = "0 20 8 * * *")
	@GetMapping("/scheduleGstr8") // ready
	public ResponseEntity<String> scheduleGstr8Download() {
		return scheduleWithLogging("R8", "scheduleGstr8Download");// Completed
	}

	@Scheduled(cron = "0 30 8 * * *")
	@GetMapping("/scheduleGstr9") // ready--
	public ResponseEntity<String> scheduleGstr9Download() {
		return scheduleWithLogging("R9", "scheduleGstr9Download");
	}

	@Scheduled(cron = "0 40 8 * * *")
	@GetMapping("/scheduleGstr9a") // ready
	public ResponseEntity<String> scheduleGstr9aDownload() {
		return scheduleWithLogging("R9A", "scheduleGstr9aDownload");
	}

	@GetMapping("/scheduleGstr98a") // ready-- no data
	public ResponseEntity<String> scheduleGstr98aDownload() {
		return scheduleWithLogging("R98A", "scheduleGstr98aDownload");
	}

	@Scheduled(cron = "0 10 9 * * *")
	@GetMapping("/scheduleGstr9c") // ready---
	public ResponseEntity<String> scheduleGstr9cDownload() {
		return scheduleWithLogging("R9C", "scheduleGstr9cDownload");
	}

	@Scheduled(cron = "0 10 9 * * *")
	@GetMapping("/scheduleGstr10") // ready---
	public ResponseEntity<String> scheduleGstr10Download() {
		return scheduleWithLogging("R10", "scheduleGstr10Download");
	}

	@Scheduled(cron = "0 20 9 * * *")
	@GetMapping("/scheduleGstr11") // ready---
	public ResponseEntity<String> scheduleGstr11Download() {
		return scheduleWithLogging("R11", "scheduleGstr11Download");
	}

	@GetMapping("/scheduleGstr3") // --notReady
	public ResponseEntity<String> scheduleGstr3Download() {
		return scheduleWithLogging("gstr3", "scheduleGstr3Download");
	}

	@GetMapping("/scheduleGstr12") // --notReady
	public ResponseEntity<String> scheduleGstr12Download() {
		return scheduleWithLogging("R12", "scheduleGstr12Download");
	}

	@GetMapping("/scheduleGstr13") // --notReady
	public ResponseEntity<String> scheduleGstr13Download() {
		return scheduleWithLogging("R13", "scheduleGstr13Download");
	}

	@GetMapping("/scheduleGstr14") // --notReady
	public ResponseEntity<String> scheduleGstr14Download() {
		return scheduleWithLogging("R14", "scheduleGstr14Download");
	}

	@GetMapping("/schedulePmt") // --notReady
	public ResponseEntity<String> schedulePmtDownload() {
		return scheduleWithLogging("PMT", "schedulePmtDownload");
	}

	@GetMapping("/scheduleGstr1r3b") // --notReady
	public ResponseEntity<String> scheduleGstr1r3bDownload() {
		return scheduleWithLogging("gstr1r3b", "scheduleGstr1r3bDownload");
	}

	@GetMapping("/saveDataIntoToDb")
	public ResponseEntity<String> saveDataFromJson(@RequestParam String application) {

		return executeWithLogging("SAVE_DATA_FROM_JSON_TO_DB", application, () -> {

			long totalRecords = commonControllerGstrUtilityImpl.saveDataFromJsonToDb(application);

			return "Successfully Processed Records : " + totalRecords;
		});
	}

	private ResponseEntity<String> executeWithLogging(String apiName, String application, Supplier<String> supplier) {

		log.info("▶️ [START] {} | Application={}", apiName, application);
		long startTime = System.currentTimeMillis();

		try {
			String response = supplier.get();
			long timeTaken = System.currentTimeMillis() - startTime;

			log.info("🏁 [END] {} | Application={} | Response={} | TimeTaken={} ms", apiName, application, response,
					timeTaken);

			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			log.error("❌ [ERROR] {} | Application={}", apiName, application, ex);
			return ResponseEntity.internalServerError().body("Internal Server Error while executing " + apiName);
		}
	}

	@GetMapping("/downloadDocument") // not working
	public ResponseEntity<List<String>> getDownloadDocument() {
		String userName = "GSTG2G18";
		List<String> responses = gstinServiceRegistration.getDownloadDocumentViaGstin(userName);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/processAllDocuments") // need to change the table
	public ResponseEntity<String> processAllDocuments() {

		String userName = "GSTG2G18";

		String response = gstinServiceRegistration.processAllDocuments(userName);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/processFewDocuments") // need to change the table--
	public ResponseEntity<String> processFewDocuments() {

		String userName = "GSTG2G18";

		String response = gstinServiceRegistration.processFewDocuments(userName);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/get-comparison-report") // Get Comparison Report
	public ResponseEntity<String> getComparisonReport() {

		String userName = "GSTG2G18";

		String response = registrationServiceImpl.processDocumentsHim(userName);

		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/process-documents-dh") // need to change the table--
	public ResponseEntity<String> processDocumentsDh() {

		String userName = "GSTG2G18";

		String response = gstinServiceRegistration.processDocumentsDh(userName);

		return ResponseEntity.ok(response);
	}

}
