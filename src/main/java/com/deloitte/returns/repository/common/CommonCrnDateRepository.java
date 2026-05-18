package com.deloitte.returns.repository.common;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.CommonCrnDate;

@Repository
public interface CommonCrnDateRepository extends JpaRepository<CommonCrnDate, Long> {

	Optional<CommonCrnDate> findByFormattedStartDateTimeAndCaseType(String formattedStartDateTime, String caseType);

	Optional<CommonCrnDate> findByFormattedStartDateTime(String formattedStartDateTime);

}
