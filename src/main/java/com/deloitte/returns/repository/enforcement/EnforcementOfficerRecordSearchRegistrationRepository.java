package com.deloitte.returns.repository.enforcement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1.EnforcementOfficerRecordSearchRegistration;

public interface EnforcementOfficerRecordSearchRegistrationRepository
		extends JpaRepository<EnforcementOfficerRecordSearchRegistration, Long> {

}
