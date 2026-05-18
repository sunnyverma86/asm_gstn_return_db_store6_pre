package com.deloitte.returns.entity;

import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.JsonNode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_names_common_adjudication", schema = "asm")
@Entity
public class CommonFileDetailsAdjudication {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long idReturnCountCrnJson;

	private String fileName;
	private String filePath;

	private String crn;
	private String statusType;

	private String startDateTime;
	private String endDateTime;

	private String application;
	
	private String url;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "json_data")
	private JsonNode jsonData;

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
