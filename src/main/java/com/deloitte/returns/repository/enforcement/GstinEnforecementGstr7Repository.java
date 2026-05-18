package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR71.GstinEnforecementGstr7;

public interface GstinEnforecementGstr7Repository extends JpaRepository<GstinEnforecementGstr7, Long> {

	List<GstinEnforecementGstr7> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr7 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
