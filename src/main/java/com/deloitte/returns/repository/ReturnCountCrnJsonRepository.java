package com.deloitte.returns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.returns.entity.filecounter.ReturnCountCrnJson;

@Repository
public interface ReturnCountCrnJsonRepository extends JpaRepository<ReturnCountCrnJson, Long> {
	@Transactional
	@Modifying
	@Query(value = """
			CALL filecounter.insert_crn(
			    CAST(:startDt AS timestamp),
			    CAST(:endDt AS timestamp),
			    :crncnt,
			    :isSuccess,
			    CAST(:msg AS jsonb),
			    CAST(:jsonData AS jsonb)
			)
			""", nativeQuery = true)
	void insertCrnData(String startDt, String endDt, Integer crncnt, Boolean isSuccess, String msg, String jsonData);

	@Query(value = """
			SELECT COUNT(*) > 0
			FROM filecounter.crn_common
			WHERE enddt = :endDt
			""", nativeQuery = true)
	boolean existsByEndDt(String endDt);

	@Query(value = """
			SELECT MAX(enddt)
			FROM filecounter.crn_common
			""", nativeQuery = true)
	String getLastProcessedEndDt();

	@Query(value = """
			SELECT *
			FROM filecounter.crn_common
			WHERE (issuccess IS NULL
			       OR issuccess = false)
			  AND msg = 'SUCCESS'
			ORDER BY "Id"
			""", nativeQuery = true)
	List<ReturnCountCrnJson> findPendingRecords();

	ReturnCountCrnJson findTopByOrderByEndDtDesc();

	

}
