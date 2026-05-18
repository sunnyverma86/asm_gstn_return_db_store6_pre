package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1.GstinEnforecementGstr3b;

@Repository
public interface GstinEnforecementGstr3bRepository extends JpaRepository<GstinEnforecementGstr3b, Long> {

	List<GstinEnforecementGstr3b> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforecementGstr3b findFirstByGstinAndRetPeriod(String gstinNumber, String retPeriod);

}
