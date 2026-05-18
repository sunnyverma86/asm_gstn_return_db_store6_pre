package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1.GstinEnforecementGstr2A;

@Repository
public interface GstinEnforecementGstr2ARepository extends JpaRepository<GstinEnforecementGstr2A, Long> {

	List<GstinEnforecementGstr2A> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr2A findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
