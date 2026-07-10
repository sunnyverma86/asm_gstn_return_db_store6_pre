package com.deloitte.returns.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.log.LedgerInitialJson;

public interface LedgerInitialJsonRepository extends JpaRepository<LedgerInitialJson, Long> {

	Page<LedgerInitialJson> findByIsSuccessIsNullAndCounterAttemptLessThan(int i, Pageable pageable);

}
