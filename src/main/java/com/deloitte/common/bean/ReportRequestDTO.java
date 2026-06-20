package com.deloitte.common.bean;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportRequestDTO {

	private LocalDate fromDate;

	private LocalDate toDate;

	private String ty;

}
