package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1.GstinEnforecementGstr8;

public interface GstinEnforecementGstr8Repository extends JpaRepository<GstinEnforecementGstr8, Long> {

	List<GstinEnforecementGstr8> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr8 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
