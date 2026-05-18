package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.GstinEnforecementGstr1;

public interface GstinEnforecementGstr1Repository extends JpaRepository<GstinEnforecementGstr1, Long> {

	List<GstinEnforecementGstr1> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr1 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
