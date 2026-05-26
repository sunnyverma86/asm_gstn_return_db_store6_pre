package com.deloitte.repository.forest;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Forest.ApiMonthlyTracking;

@Repository
public interface ApiMonthlyTrackingRepository extends JpaRepository<ApiMonthlyTracking, Long> {

	boolean existsByFromDateAndToDate(LocalDate fromDate, LocalDate toDate);

	boolean existsByFromDateAndToDateAndStatus(LocalDate fromDate, LocalDate toDate, String string);

	@Query("""
			SELECT COUNT(a)
			FROM ApiMonthlyTracking a
			WHERE a.fromDate = :fromDate
			AND a.toDate = :toDate
			AND a.status = :status
			""")
	long countRecords(LocalDate fromDate, LocalDate toDate, String status);

}
