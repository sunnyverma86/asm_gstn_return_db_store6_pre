package com.deloitte.returns.entity.regis;

import java.sql.Timestamp;

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
@Table(name = "registration_data_json_file_view", schema = "asm")
public class RegistrationDataJsonFileView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String filePath;

	private Boolean downloadFileStatus;

	private Boolean isSuccess = false;
	
	private Boolean isProcessed = false;

	private String identityType;

	private String gstinNumber;

	private Timestamp insertDt;
}