package com.deloitte.returns.repository.eway;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.AEwayBill.EwbDetailsData;

@Repository
public interface EwbDetailsDataRepository extends JpaRepository<EwbDetailsData, Long> {

	@Query("""
			    SELECT COUNT(d)
			    FROM EwbDetailsData d
			    WHERE d.ewbDt = :date
			    AND d.ewbCategory = :category
			    AND d.returnFileCountId = :countId
			    AND d.isSuccess = true
			""")
	int countSuccessFiles(@Param("date") String date, @Param("category") String category,
			@Param("countId") Long countId);

	@Query("""
			SELECT d.fileNum
			FROM EwbDetailsData d
			WHERE d.ewbDt = :date
			AND d.ewbCategory = :category
			AND d.isSuccess = false
			""")
	List<Integer> findFailedFileNumbers(String date, String category);

}
