package com.deloitte.returns.repositoryCommon;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.AlertRegistration;

@Repository
public interface AlertRegistrationRepository extends JpaRepository<AlertRegistration, Long> {

	Optional<AlertRegistration> findByStartTmAndYear(LocalDateTime startTm, String year);

	@Procedure(procedureName = "gst_api_registration.save_registration_alert")
	void saveRegistrationAlert(

			@Param("_start_tm") Timestamp startTm,

			@Param("_end_tm") Timestamp endTm,

			@Param("_issuccess") Boolean isSuccess,

			@Param("_msg") String msg,

			@Param("_jsondata") String jsonData);

	@Query(value = """
			SELECT *
			FROM gst_api_registration."ALERT_REGISTRATION"
			ORDER BY "ALERT_ID" DESC
			LIMIT 1
			""", nativeQuery = true)
	Optional<AlertRegistration> getLastProcessedAlert();

}