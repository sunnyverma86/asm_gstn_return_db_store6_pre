package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1.GstinEnforecementGstr6;

public interface GstinEnforecementGstr6Repository extends JpaRepository<GstinEnforecementGstr6, Long> {

	List<GstinEnforecementGstr6> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr6 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
