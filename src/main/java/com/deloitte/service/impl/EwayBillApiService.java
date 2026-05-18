package com.deloitte.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.deloitte.common.bean.DateHepler;
import com.deloitte.returns.entity.AEwayBill.DateInfoEwayBill;
import com.deloitte.returns.entity.AEwayBill.EWayBillAuthBean;
import com.deloitte.returns.entity.AEwayBill.EwayFileCountResponse;
import com.deloitte.returns.entity.AEwayBill.EwbCountData;
import com.deloitte.service.abs.AbstractEwayBillApiService;

import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.JsonNode;

@Service
@Log4j2
public class EwayBillApiService extends AbstractEwayBillApiService {
	// 1:
	public String scheduleEwayBillDownload(String category) {

		log.info("▶️ [START] scheduleEwayBillDownload | category={}", category);

		long startTime = System.currentTimeMillis();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			LocalDate endDateLocal = LocalDate.now().minusDays(7);
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

		DateInfoEwayBill existingDate = dateInfoEwayBillRepository.findDateDataByDateAndCategory(date, category);

		String response = null;

		if (existingDate != null) {

			log.info("Date already exists in DB. date={}, isProcessed={}", date, existingDate.getIsProcessed());

			if (Boolean.FALSE.equals(existingDate.getIsProcessed())) {

				log.info("Processing pending date. date={}", date);

				response = beforeGetEwayBillFromGstn(category, date);// 1:1:1:1:1

				log.info("Processing completed for existing date. date={}, response={}", date, response);
			} else {
				log.info("Skipping already processed date. date={}", date);
			}

			return response;
		}

		log.info("New date detected. Inserting into DB. date={}", date);

		DateInfoEwayBill objDateDataEwayBill = new DateInfoEwayBill();
		objDateDataEwayBill.setIsProcessed(false);
		objDateDataEwayBill.setCategory(category);
		objDateDataEwayBill.setDate(date);

		try {
			dateInfoEwayBillRepository.save(objDateDataEwayBill);

			log.info("Date inserted successfully. date={}", date);

			response = beforeGetEwayBillFromGstn(category, date);// 1:1:1:1:1

			log.info("Processing completed for new date. date={}, response={}", date, response);

		} catch (Exception e) {
			log.error("Failed to process date. date={}", date, e);
		}

		return response;
	}

	// 1:1:1:1:1
	private String beforeGetEwayBillFromGstn(String category, String date) {

		log.info("Starting EwayBill fetch from GSTN. category={}, date={}", category, date);

		String responseBody = getEWBFileCount(date, category);

		log.debug("GSTN Response received. category={}, date={}, response={}", category, date, responseBody);

		if (StringUtils.isNotBlank(responseBody) && responseBody
				.contains("File download and extraction successful: File downloaded and extracted successfully")) {

			log.info("File download successful. Updating DB status. category={}, date={}", category, date);

			DateInfoEwayBill dateDataEwayBill = dateInfoEwayBillRepository.findByDateAndCategory(date, category);

			if (dateDataEwayBill != null) {

				log.debug("DB Record found. Updating isProcessed flag. date={}", dateDataEwayBill.getDate());

				dateDataEwayBill.setIsProcessed(true);
				dateInfoEwayBillRepository.save(dateDataEwayBill);

				log.info("DB updated successfully. isProcessed=true, date={}", date);

			} else {
				log.warn("DB record not found for update. category={}, date={}", category, date);
			}
		} else {
			log.warn("EwayBill download failed or unexpected response. category={}, date={}, response={}", category,
					date, responseBody);
		}

		return responseBody;
	}

//============================REAL WORKING START FROM HERE===================
	// 1:1:1:1:1:1 REAL WORKING START FROM HERE
	public String getEWBFileCount(String date, String category) {

		try {
			log.info("Starting EWB file count process | date: {} | category: {}", date, category);

			EWayBillAuthBean authBean = supportEwayBillApiService.getLatestAuth();// 1
			log.info("Starting EWB file count process:11:"+authBean);
			JsonNode countResponse = supportEwayBillApiService.fetchFileCountResponse(date, category, authBean);// 2
			log.info("Starting EWB file count process:12:");
			EwayFileCountResponse countEntity = supportEwayBillApiService.handleFileCountResponse(countResponse, date);// 3
			log.info("Starting EWB file count process:15:"+countEntity.getDt());
			if (!countEntity.getIsSuccess()) {
				return countEntity.getMsg();
			}
			log.info("Starting EWB file count process:16:"+countResponse);
			EwbCountData countData = supportEwayBillApiService.saveCountData(countResponse);// 4
			log.info("Starting EWB file count process:17:"+countData.getId());
			supportEwayBillApiService.processFileDetails(countData, authBean, date, category,
					countEntity.getReturnFileCountId());//5

			return "File download and processing completed successfully.";

		} catch (Exception e) {
			log.error("Error processing EWB file count", e);
			return "Error: " + e.getMessage();
		}
	}

}
