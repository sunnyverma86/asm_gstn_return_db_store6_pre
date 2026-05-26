package com.deloitte.repository.forest;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.Forest.ExtractionLog;

public interface ExtractionLogRepository extends JpaRepository<ExtractionLog, Long> {

	boolean existsByFromDateAndToDateAndPage(LocalDate fromDate, LocalDate toDate, int page);

}
