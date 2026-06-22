package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.ReturnComparisonReportGstinJson;

@Repository
public interface ReturnComparisonReportGstinJsonRepository
		extends JpaRepository<ReturnComparisonReportGstinJson, Long> {

	boolean existsByGstinAndFy(String gstin, String fy);

	Page<ReturnComparisonReportGstinJson> findByIsProcessedNullOrIsProcessedFalse(Pageable pageable);

	boolean existsByGstinAndFyAndIsProcessed(String gstin, String fy, boolean b);

	Page<ReturnComparisonReportGstinJson> findByIsProcessedNullOrIsProcessedFalseAndCounterAttemptLessThan(int i,
			Pageable pageable);

}
