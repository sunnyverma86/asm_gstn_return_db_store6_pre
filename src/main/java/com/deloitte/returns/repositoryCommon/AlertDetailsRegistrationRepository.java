package com.deloitte.returns.repositoryCommon;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.AlertDetailsRegistration;

@Repository
public interface AlertDetailsRegistrationRepository extends JpaRepository<AlertDetailsRegistration, Long> {

	List<AlertDetailsRegistration> findByIsSuccessIsNullAndEntityIdIsNotNull();

	List<AlertDetailsRegistration> findByIsSuccessIsNullAndEntityIdIsNotNullAndCreateDateTimeIsNotNull();

	List<AlertDetailsRegistration> findByIsSuccessIsNullAndEntityIdIsNotNullAndCreateDateTimeIsNotNullAndPartitionFyGreaterThanEqual(
			LocalDate partitionDate);

	List<AlertDetailsRegistration> findByIsSuccessTrueAndIsEntitySuccessIsNullAndEntityJsonIsNullAndPartitionFyGreaterThanEqual(
			LocalDate partitionDate);

}
