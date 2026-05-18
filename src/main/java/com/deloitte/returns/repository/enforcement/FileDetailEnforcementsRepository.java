package com.deloitte.returns.repository.enforcement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.FileNameEnforcement;

@Repository
public interface FileDetailEnforcementsRepository extends JpaRepository<FileNameEnforcement, Long> {

}
