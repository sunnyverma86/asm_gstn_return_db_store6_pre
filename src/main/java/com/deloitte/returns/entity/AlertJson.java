package com.deloitte.returns.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "return_file_count", schema = "asm")
@Table(name = "alert_common", schema = "filecounter")
public class AlertJson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String startDt;

	private String endDt;

	private Integer crncnt;

	private LocalDateTime activityDt;

	private Boolean isSuccess;

	private String msg;

	private String caseType;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "json_data")
	private JsonNode jsonData;

}
