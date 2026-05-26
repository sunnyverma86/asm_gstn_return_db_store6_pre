package com.deloitte.returns.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.GstinEntity;

@Repository
public interface GstinRepository extends JpaRepository<GstinEntity, Long> {


	GstinEntity findFirstByGstinAndRetPeriod(String gstin, String retPeriod);

	//List<GstinEntity> findAllByIsProcessedFalseAndTaxpayerTypeOrderById(String taxpayerType);

	List<GstinEntity> findAllByIsProcessedRegistrationFalseAndTaxpayerTypeOrderById(String taxpayerType);

	List<GstinEntity> findAllByIsProcessedGstr2aFalseAndFoundInfoTrueOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerCashFalseAndFoundInfoTrueOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerTaxFalseAndFoundInfoTrueOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerItcFalseAndFoundInfoTrueOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerOtherFalseAndFoundInfoTrueOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerCashFalseOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerTaxFalseOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerItcFalseOrderById();

	List<GstinEntity> findAllByIsProcessedLedgerOtherFalseOrderById();

	

}
