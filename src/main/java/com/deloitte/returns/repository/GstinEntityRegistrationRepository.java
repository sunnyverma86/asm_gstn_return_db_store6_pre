package com.deloitte.returns.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.GstinEntityRegistration;

@Repository
public interface GstinEntityRegistrationRepository extends JpaRepository<GstinEntityRegistration, Long> {

	Optional<GstinEntityRegistration> findByGstinNumber(String gstin);

}
