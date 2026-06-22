package com.deloitte.returns.entity;

import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "return_comparison_report_gstin_json", schema = "log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnComparisonReportGstinJson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "fy")
	private String fy;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "error_message")
	private String errorMessage;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "jsondata")
	private JsonNode jsonData;

	@Column(name = "counter_attempt") //
	private int counterAttempt;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "create_date_time", updatable = false)
	private Instant createDateTime;

	@Column(name = "updated_date_time")
	private Instant updatedDateTime;

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
