package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.common.entity.ReturnDateLog;

@Repository
public interface ReturnDateLogRepository extends JpaRepository<ReturnDateLog, Long> {

	ReturnDateLog findTopByOrderByIdDesc();

}
