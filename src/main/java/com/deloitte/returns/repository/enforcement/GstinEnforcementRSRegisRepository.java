package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1.GstinEnforcementRSRegis;

@Repository
public interface GstinEnforcementRSRegisRepository extends JpaRepository<GstinEnforcementRSRegis, Long> {

	List<GstinEnforcementRSRegis> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

	GstinEnforcementRSRegis findFirstByGstin(String gstinNumber);

}
