package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1.GstinEnforecementGstr9;

@Repository
public interface GstinEnforecementGstr9Repository extends JpaRepository<GstinEnforecementGstr9, Long> {

	List<GstinEnforecementGstr9> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr9 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
