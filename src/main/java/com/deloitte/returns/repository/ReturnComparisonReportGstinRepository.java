package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.ReturnComparisonReportGstin;

@Repository
public interface ReturnComparisonReportGstinRepository extends JpaRepository<ReturnComparisonReportGstin, Long> {

	Page<ReturnComparisonReportGstin> findByIsProcessedNullOrIsProcessedFalse(Pageable pageable);

	Page<ReturnComparisonReportGstin> findByIsProcessedNullOrIsProcessedFalseAndCounterAttemptLessThan(
			int counterAttempt, Pageable pageable);

	@Query("""
			SELECT r
			FROM ReturnComparisonReportGstin r
			WHERE
			(r.isProcessed = false
			OR r.isProcessed IS NULL)
			AND
			(r.isProcessing = false
			OR r.isProcessing IS NULL)
			AND r.counterAttempt < :attempt
			""")
	Page<ReturnComparisonReportGstin> findPendingRecords(@Param("attempt") int attempt, Pageable pageable);

}
