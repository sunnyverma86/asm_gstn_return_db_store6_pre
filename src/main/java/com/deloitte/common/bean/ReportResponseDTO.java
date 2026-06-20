package com.deloitte.common.bean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {

	private LocalDate dt;

	private Integer numFiles;

	private Integer fileNum;

	private Integer numFilesCnt;

	private LocalDate dt2;

	private Integer fileNumber;

	private Long jsonCount;

}
