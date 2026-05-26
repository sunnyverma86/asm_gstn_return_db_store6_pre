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
@Table(name = "api_monthly_tracking", schema = "analytics")
@Data
public class ApiMonthlyTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_monthly_tracking_seq")
	@SequenceGenerator(name = "api_monthly_tracking_seq", sequenceName = "analytics.api_monthly_tracking_seq", allocationSize = 1)
	private Long id;

	private LocalDate fromDate;
	private LocalDate toDate;

	private Integer totalRecords;
	private String status;

	private LocalDateTime startedAt;
	private LocalDateTime completedAt;

	private String errorMessage;
}
