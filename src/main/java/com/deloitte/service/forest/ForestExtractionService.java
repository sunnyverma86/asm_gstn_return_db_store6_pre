package com.deloitte.service.forest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.repository.forest.ExtractionLogRepository;
import com.deloitte.repository.forest.ForestPaymentDetailsRepository;
import com.deloitte.returns.entity.Forest.ExtractionLog;
import com.deloitte.returns.entity.Forest.ForestPaymentDetails;
import com.deloitte.returns.entity.Forest.Model.ForestApiResponse;
import com.deloitte.returns.entity.Forest.Model.ForestPaymentDto;
import com.deloitte.service.utility.ForestApiClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ForestExtractionService {

	@Autowired
	private ForestApiClient apiClient;

	@Autowired
	private ForestPaymentDetailsRepository repository;

	@Autowired
	private ExtractionLogRepository logRepo;

	private static final int PER_PAGE = 100;
	private static final int MAX_RETRY = 3;

	public void startExtraction(LocalDate start, LocalDate end) {

		LocalDate current = start.withDayOfMonth(1);

		while (!current.isAfter(end)) {

			LocalDate fromDate = current.withDayOfMonth(1);
			LocalDate toDate = current.withDayOfMonth(current.lengthOfMonth());

			log.info("📅 Processing Month {} to {}", fromDate, toDate);

			processMonth(fromDate, toDate);

			current = current.plusMonths(1);
		}
	}

	private void processMonth(LocalDate fromDate, LocalDate toDate) {

		int page = 1;
		boolean hasMore = true;

		while (hasMore) {

			if (logRepo.existsByFromDateAndToDateAndPage(fromDate, toDate, page)) {
				log.info("⏭ Skipping duplicate run {} - {} page {}", fromDate, toDate, page);
				page++;
				continue;
			}

			ExtractionLog logEntity = new ExtractionLog();
			logEntity.setFromDate(fromDate);
			logEntity.setToDate(toDate);
			logEntity.setPage(page);
			logEntity.setStartTime(LocalDateTime.now());
			logEntity.setStatus("STARTED");
			logRepo.save(logEntity);

			try {
				ForestApiResponse response = apiClient.fetchMonthlyData(fromDate, toDate, page, PER_PAGE, MAX_RETRY);

				if (response == null || response.getResponse() == null) {
					logEntity.setStatus("FAILED");
					logEntity.setErrorMessage("NULL_RESPONSE");
					logRepo.save(logEntity);
					return;
				}

				saveBatch(response.getResponse());

				logEntity.setRecordCount(response.getResponse().size());
				logEntity.setStatus("SUCCESS");
				logEntity.setEndTime(LocalDateTime.now());
				logRepo.save(logEntity);

				hasMore = response.isHas_more();
				page++;

			} catch (Exception ex) {
				logEntity.setStatus("FAILED");
				logEntity.setErrorMessage(ex.getMessage());
				logRepo.save(logEntity);

				log.error("❌ month={} page={} err={}", fromDate, page, ex.getMessage());
				return;
			}
		}
	}

	private void saveBatch(List<ForestPaymentDto> list) {

		int batchSize = 50;
		List<ForestPaymentDetails> buffer = new ArrayList<>();

		for (ForestPaymentDto dto : list) {

			try {
				buffer.add(map(dto));

				if (buffer.size() == batchSize) {
					repository.saveAll(buffer);
					buffer.clear();
				}

			} catch (Exception ex) {
				log.error("⚠ mapping_fail pan={} err={}", dto.getPan(), ex.getMessage());
			}
		}

		if (!buffer.isEmpty()) {
			repository.saveAll(buffer);
		}
	}

	private ForestPaymentDetails map(ForestPaymentDto dto) {
		ForestPaymentDetails e = new ForestPaymentDetails();
		e.setStakeholder(dto.getStakeholder());
		e.setPan(dto.getPan());
		e.setGstNo(dto.getGst_no());
		e.setDfoGstNo(dto.getDfo_gstno());
		e.setPaymentType(dto.getPayment_type());
		e.setAmount(new BigDecimal(dto.getAmount()));
		e.setPaymentDate(LocalDate.parse(dto.getPayment_date()));
		return e;
	}
}
