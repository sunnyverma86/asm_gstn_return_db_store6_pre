package com.deloitte.tab.bean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrnReportResponseDTO {

	private LocalDate startDate;

	private String caseType;

	private Integer downloadCount;

	private Integer insertCount;

}