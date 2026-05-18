package com.deloitte.returns.repository.enforcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.FileNameEnforcement;

public interface FileNameEnforcementRepository extends JpaRepository<FileNameEnforcement, Long> {

	List<FileNameEnforcement> findAllByIsProcessedFalseOrderById();

}
