package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1.GstinEnforcementRSE;

@Repository
public interface GstinEnforcementRSERepository extends JpaRepository<GstinEnforcementRSE, Long> {

	List<GstinEnforcementRSE> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforcementRSE findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
