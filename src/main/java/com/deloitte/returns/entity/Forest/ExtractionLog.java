package com.deloitte.returns.entity.Forest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "extraction_log", schema = "analytics")
@Data
public class ExtractionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extraction_log_seq")
	@SequenceGenerator(name = "extraction_log_seq", sequenceName = "analytics.extraction_log_seq", allocationSize = 1)
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
