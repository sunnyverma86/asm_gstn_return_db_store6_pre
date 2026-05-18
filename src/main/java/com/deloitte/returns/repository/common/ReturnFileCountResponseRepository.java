package com.deloitte.returns.repository.common;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.ReturnFileCountResponse;

@Repository
public interface ReturnFileCountResponseRepository extends JpaRepository<ReturnFileCountResponse, Long> {

	Optional<ReturnFileCountResponse> findTopByTyAndMsgNotAndCounterAttemptLessThanOrderByDtDesc(String application,
			String success, int i);

	Optional<ReturnFileCountResponse> findTopByTyAndMsgOrderByDtDesc(String application, String success);

	Optional<ReturnFileCountResponse> findByTyAndDt(String application, Date sqlDate);

	Optional<ReturnFileCountResponse> findTopByTyAndMsgNotAndCounterAttemptLessThanAndDtGreaterThanOrderByDtDesc(
			String ty, String msg, int counterAttempt, Date fromDate);

	Optional<ReturnFileCountResponse> findTopByTyAndIsSuccessFalseAndCounterAttemptLessThanAndDtGreaterThanOrderByDtDesc(
			String application, int i, Date valueOf);
	
	Optional<ReturnFileCountResponse> findByDtAndTy(Date dt, String ty);

	List<ReturnFileCountResponse> findAllByTyAndIsSuccessFalseAndCounterAttemptLessThanAndDtGreaterThanOrderByDtDesc(
			String application, int i, Date valueOf);


}
