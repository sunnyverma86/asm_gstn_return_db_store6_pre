package com.deloitte.returns.repository.common;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.CommonAlertDate;
import com.deloitte.returns.entity.CommonCrnDate;

@Repository
public interface CommonAlertDateRepository extends JpaRepository<CommonAlertDate, Long> {

	Optional<CommonAlertDate> findByFormattedStartDateTimeAndYear(String formattedStartDateTime, String year);

}
	