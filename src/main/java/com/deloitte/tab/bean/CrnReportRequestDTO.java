package com.deloitte.tab.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrnReportRequestDTO {

	private String fromDate;

	private String toDate;
	
	private String caseType;

}
