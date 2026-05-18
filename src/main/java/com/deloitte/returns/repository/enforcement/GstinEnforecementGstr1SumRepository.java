package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1Sum1.GstinEnforecementGstr1sum;

public interface GstinEnforecementGstr1SumRepository extends JpaRepository<GstinEnforecementGstr1sum, Long> {

	List<GstinEnforecementGstr1sum> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr1sum findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
