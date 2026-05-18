package com.deloitte.returns.repository.ledger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.LedgerCash.GstinLedger;

@Repository
public interface GstinLedgerRepository extends JpaRepository<GstinLedger, Long> {

	List<GstinLedger> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	List<GstinLedger> findAllByIsProcessedFalseAndFoundInfoTrueAndApplicationOrderById(String action);

}
