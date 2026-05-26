package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.ArnDetailCommonRegistration;

@Repository
public interface ArnDetailCommonRegistrationRepository extends JpaRepository<ArnDetailCommonRegistration, Long> {

}
