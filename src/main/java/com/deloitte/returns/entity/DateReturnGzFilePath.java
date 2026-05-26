package com.deloitte.returns.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "date_return_gz_file_path", schema = "common")
public class DateReturnGzFilePath {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long returnFileDetailPrimaryId;
	private Long returnFileCountPrimaryId;

	private String filePath;
	private String application;
	private String dt;

	private Boolean isProcessed = false;

	private Integer sequenceNumber;

	private LocalDateTime createDateTime = LocalDateTime.now();

}
