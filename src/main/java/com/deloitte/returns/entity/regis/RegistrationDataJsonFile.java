package com.deloitte.returns.entity.regis;

import java.sql.Timestamp;

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
@Table(name = "registration_data_json_file", schema = "asm")
public class RegistrationDataJsonFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String filePath;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "json_data")
	private JsonNode jsonData;

	private int fileNumber;

	private Boolean downloadFileStatus;

	private Boolean isSuccess = false;

	private String identityType;
	
	private String gstinNumber;

	private Timestamp insertDt;
}