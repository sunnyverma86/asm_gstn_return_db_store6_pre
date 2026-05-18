package com.deloitte.returns.repository.enforcement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1.EnforcementOfficerRecordSearchReturns;

public interface EnforcementOfficerRecordSearchReturnsRepository
		extends JpaRepository<EnforcementOfficerRecordSearchReturns, Long> {

}
