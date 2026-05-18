package com.deloitte.controller;

import java.io.UnsupportedEncodingException;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.service.impl.ArnHandlerForRegistration;
import com.deloitte.service.impl.GstinExcelService;
import com.deloitte.service.support.GstinServiceRegistration;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/common/registration")
@Log4j2
public class RegistrationController {

	@Autowired
	private GstinServiceRegistration gstinService;

	@Autowired
	private GstinExcelService gstinExcelService;

	@Autowired
	private ArnHandlerForRegistration arnHandlerForRegistration;

	// =========================================================
	// COMPLETE AUTOMATION FLOW
	// 1. ALERT API-->case-alert-automatically
	// 2. ARN HANDLER-->arn-handler-automatically
	// 3. REGISTRATION API-->all-registration-automatically
	// =========================================================

	@GetMapping("/complete-registration-automation")
	public ResponseEntity<String> completeRegistrationAutomation() {

		log.info("=====================================================");
		log.info("▶️ COMPLETE REGISTRATION AUTOMATION STARTED");
		log.info("=====================================================");

		long startTime = System.currentTimeMillis();

		try {

			String finalResponse = gstinService.executeCompleteAutomation();

			long totalTime = System.currentTimeMillis() - startTime;

			log.info("=====================================================");
			log.info("🏁 COMPLETE REGISTRATION AUTOMATION COMPLETED");
			log.info("⏱️ TOTAL TIME={} ms", totalTime);
			log.info("=====================================================");

			return ResponseEntity.ok(finalResponse);

		} catch (Exception ex) {

			long totalTime = System.currentTimeMillis() - startTime;

			log.error("❌ COMPLETE AUTOMATION FAILED");
			log.error("⏱️ FAILED AFTER={} ms", totalTime, ex);

			return ResponseEntity.internalServerError().body("COMPLETE AUTOMATION FAILED : " + ex.getMessage());
		}
	}

	@GetMapping("/case-alert-automatically")
	public String getCrnForCaseType() throws UnsupportedEncodingException {

		log.info("Received request to fetch ALERT automatically");

		String response = gstinService.processAlertAutomatically();

		log.info("ALERT fetch completed successfully");

		return response;
	}

	@GetMapping("/arn-handler-automatically")
	public String arnHanddler() throws UnsupportedEncodingException {

		log.info("Received request to fetch ARN automatically");

		String response = arnHandlerForRegistration.processJson();

		log.info("ALERT fetch completed successfully");

		return response;
	}

	@GetMapping("/all-registration-automatically")
	public String registrationHanddler() throws UnsupportedEncodingException {

		log.info("Received request to fetch ALERT automatically");

		String response = arnHandlerForRegistration.processRegistration();

		log.info("ALERT fetch completed successfully");

		return response;
	}
	
	
	
	
	
	//not in use please don't use these below

	// ===========Fetch CRN based on case type and month range====//
	@GetMapping("/case-alert")
	public String getCrnForCaseType(@RequestParam int year, @RequestParam int startMonth, @RequestParam int endMonth)
			throws UnsupportedEncodingException {

		log.info("Received request to fetch CRN |  year={} startMonth={} endMonth={}", year, startMonth, endMonth);

		String response = gstinService.processDateAccordingToStartAndEndMonth(year, startMonth, endMonth);

		log.info("ALERT fetch completed successfully for year={}", year);

		return response;
	}

	// API 2: Process Excel from server path
	@PostMapping("/process")
	public ResponseEntity<String> processExcelFromPath(@RequestParam String filePath) {

		try {

			gstinExcelService.processCsv(filePath);

			return ResponseEntity.ok("✅ Excel processed successfully");

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("❌ Error processing file: " + e.getMessage());
		}
	}

	/**
	 * 1️⃣ Download registration data from GSTN and save into folder + DB
	 */
	@GetMapping("/gstn/{identityType}")
	public ResponseEntity<String> downloadFromGstn(@PathVariable String identityType) {

		return executeWithLogging(identityType, "downloadFromGstn",
				() -> gstinService.getAllAndSaveIntoDbViaGstin(identityType));
	}

	/**
	 * 2️⃣ Process JSON files and save into database
	 */
	@GetMapping("/process/{identityType}")
	public ResponseEntity<String> processRegistration(@PathVariable String identityType) {

		return executeWithLogging(identityType, "processRegistrationJson",
				() -> gstinService.processRegistrationJson(identityType));
	}

	/**
	 * Common logging wrapper for controller APIs
	 */
	private ResponseEntity<String> executeWithLogging(String identityType, String apiName,
			Supplier<String> serviceCall) {

		log.info("▶️ [START] {} | IdentityType={}", apiName, identityType);

		long startTime = System.currentTimeMillis();

		String response = serviceCall.get();

		long timeTaken = System.currentTimeMillis() - startTime;

		log.info("🏁 [END] {} | IdentityType={} | Response={} | TimeTaken={} ms", apiName, identityType, response,
				timeTaken);

		return ResponseEntity.ok(response);
	}
	//
	// GET /common/registration/gstn/normal
	// GET /common/registration/gstn/tds
	// GET /common/registration/gstn/tcs
	// GET /common/registration/gstn/composition

	// GET /common/registration/process/normal
	// GET /common/registration/process/tds
	// GET /common/registration/process/tcs
	// GET /common/registration/process/composition

	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {

		try {

			gstinExcelService.processExcel(file);

			return ResponseEntity.ok("✅ Excel data imported successfully");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.internalServerError().body("❌ Error processing Excel file: " + e.getMessage());
		}
	}

}