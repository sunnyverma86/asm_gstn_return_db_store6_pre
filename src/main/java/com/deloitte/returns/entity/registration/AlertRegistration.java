package com.deloitte.returns.entity.registration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"ALERT_REGISTRATION\"", schema = "gst_api_registration")
public class AlertRegistration {
//
//	@Id
//	@SequenceGenerator(name = "alert_registration_seq_generator", sequenceName = "gst_api_registration.alert_registration_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alert_registration_seq_generator")
	@Column(name = "\"ALERT_ID\"")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long alertId;

	@Column(name = "start_tm")
	private LocalDateTime startTm;

	@Column(name = "end_tm")
	private LocalDateTime endTm;

	@Column(name = "msg", length = 500)
	private String msg;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "jsondata")
	private String jsonData;

	@Column(name = "\"issuccess\"")
	private Boolean isSuccess;

	@Column(name = "partition_fy")
	private LocalDate partitionFy;

	@Column(name = "create_date_time", updatable = false)
	private Instant createDateTime;

	@Column(name = "updated_date_time")
	private Instant updatedDateTime;

	@Column(name = "year")
	private String year;

	@Column(name = "day_count")
	private Long dayCount;

	@Column(name = "status")
	private String status;

	@PrePersist
	protected void onCreate() {
		Instant now = Instant.now();
		this.createDateTime = now;
		this.updatedDateTime = now;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDateTime = Instant.now();
	}
}


