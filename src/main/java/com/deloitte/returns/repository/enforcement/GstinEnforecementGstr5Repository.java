package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1.GstinEnforecementGstr5;
@Repository
public interface GstinEnforecementGstr5Repository extends JpaRepository<GstinEnforecementGstr5, Long> {

	List<GstinEnforecementGstr5> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr5 findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
