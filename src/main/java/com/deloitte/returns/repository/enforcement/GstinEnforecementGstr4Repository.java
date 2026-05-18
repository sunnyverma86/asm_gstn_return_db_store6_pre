package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1.GstinEnforecementGstr4;

@Repository
public interface GstinEnforecementGstr4Repository extends JpaRepository<GstinEnforecementGstr4, Long> {

	List<GstinEnforecementGstr4> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr4 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
