package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.regis.RegistrationDataJsonFile;

@Repository
public interface RegistrationDataJsonFileRepository extends JpaRepository<RegistrationDataJsonFile, Long> {

}
