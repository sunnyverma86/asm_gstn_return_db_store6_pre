package com.deloitte.tab.bean;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrationReportEntity {
	private LocalDate startDate;
	private Long totalFetchArn;
	private Long totalArnSuccess;
	private Long totalArnFailure;
	private Long totalFetchEntity;
	private Long totalFetchSuccessEntity;
	private Long totalFetchFailureEntity;
	private Long insertCount;
}
