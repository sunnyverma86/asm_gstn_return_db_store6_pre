package com.deloitte.returns.entity.Forest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "extraction_log", schema = "analytics")
@Data
public class ExtractionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fromDate;
	private LocalDate toDate;

	private Integer totalRecords;
	private String status;

	private LocalDateTime startedAt;
	private LocalDateTime completedAt;

	private String errorMessage;

	private Integer page;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private Integer recordCount;

}
