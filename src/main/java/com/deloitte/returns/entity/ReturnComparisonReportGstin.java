package com.deloitte.returns.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "return_comparison_report_gstin", schema = "log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnComparisonReportGstin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "fy")
	private String fy;

	@Column(name = "counter_attempt") //
	private int counterAttempt;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "is_processing")
	private Boolean isProcessing;

}
