package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registds.RegistrationTdsTcs;

@Repository
public interface TdsTcsRegistrationRepository extends JpaRepository<RegistrationTdsTcs, Long> {

}
