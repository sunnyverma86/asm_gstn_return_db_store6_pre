package com.deloitte.returns.entity.registration;

import java.time.Instant;
import java.time.LocalDateTime;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crn_common", schema = "filecounter")
public class ReturnCountCrnJson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"Id\"")
	private Long id;

	@Column(name = "\"startdt\"")
	private String startDt;

	@Column(name = "\"enddt\"")
	private String endDt;

	@Column(name = "\"crncnt\"")
	private Integer crncnt;

	@Column(name = "\"activitydt\"")
	private LocalDateTime activityDt;

	@Column(name = "\"issuccess\"")
	private Boolean isSuccess;

	@Column(name = "msg")
	private String msg;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "\"crndata\"")
	private JsonNode jsonData;

	@Column(name = "url")
	private String url;

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
