package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1.GstinEnforcementRSP;

public interface GstinEnforcementRSPRepository extends JpaRepository<GstinEnforcementRSP, Long> {

	List<GstinEnforcementRSP> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforcementRSP findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
