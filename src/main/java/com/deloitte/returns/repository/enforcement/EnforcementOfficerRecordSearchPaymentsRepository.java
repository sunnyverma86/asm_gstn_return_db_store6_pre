package com.deloitte.returns.repository.enforcement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1.EnforcementOfficerRecordSearchPayments;

public interface EnforcementOfficerRecordSearchPaymentsRepository
		extends JpaRepository<EnforcementOfficerRecordSearchPayments, Long> {

}
