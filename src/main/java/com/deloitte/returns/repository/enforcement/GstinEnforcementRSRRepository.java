package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1.GstinEnforcementRSR;

@Repository
public interface GstinEnforcementRSRRepository extends JpaRepository<GstinEnforcementRSR, Long> {

	List<GstinEnforcementRSR> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforcementRSR findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
