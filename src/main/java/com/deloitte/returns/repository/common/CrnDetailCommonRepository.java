package com.deloitte.returns.repository.common;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.CrnDetailCommon;

@Repository
public interface CrnDetailCommonRepository extends JpaRepository<CrnDetailCommon, Long> {

	List<CrnDetailCommon> findByIsProcessedFalseOrIsProcessedIsNull();

	@Query("""
			SELECT c
			FROM CrnDetailCommon c
			WHERE (c.isSuccess = false OR c.isSuccess IS NULL)
			AND c.isSuccess IS NULL
			""")
	Page<CrnDetailCommon> findPendingRecords(Pageable pageable);

	@Query("SELECT COUNT(c) FROM CrnDetailCommon c WHERE c.isProcessed = false")
	long countPendingRecords();

	@Query("""
			SELECT c
			FROM CrnDetailCommon c
			WHERE c.idReturnCountCrnJson > :id
			AND (c.isSuccess IS NULL OR c.isSuccess = false)
			AND (c.isFuture IS NULL OR c.isFuture = false)
			AND (c.isProcessed IS NULL OR c.isProcessed = false)
			AND c.counterAttempt < 6
			ORDER BY c.id DESC
			""")
	Page<CrnDetailCommon> findPendingRecordsAfterId(@Param("id") Long id, Pageable pageable);

	Page<CrnDetailCommon> findByIdReturnCountCrnJsonGreaterThanAndIsSuccessFalseAndIsFutureFalseAndIsProcessedFalseAndCounterAttemptLessThanOrderByIdDesc(
			Long id, int counterAttempt, Pageable pageable);

}

//
// -- c."jsondata" IS NULL
// -- OR

//@Query(value = """
//		SELECT *
//		FROM filecounter.crn_detail_common c
//		WHERE c."crn_id" > :id
//		AND (c."issuccess" IS NULL OR c."issuccess" = false)
//		AND (c.is_processed IS NULL OR c.is_processed = false)
//		AND (
//
//		         c."jsondata"->'error'->>'error_cd' IS DISTINCT FROM 'CM_GCAC1007'
//		    )
//		ORDER BY c."details_id" DESC
//		""", nativeQuery = true)
//Page<CrnDetailCommon> findPendingRecordsAfterId(@Param("id") Long id, Pageable pageable);