package com.deloitte.returns.entity;

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
@Table(name = "date_eway_gz_file_path", schema = "common")
public class DateEwayGzFilePath {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean isProcessed = false;

	private Long returnFileDetailPrimaryId;

	private Long returnFileCountPrimarId;

	private String filePath;

	private Integer fileNumber;

	private Integer sequenceNumber;

	private String dt;

	private String category;

	private Timestamp insertDt;
	
	


}
