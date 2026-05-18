package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessResult {

	private int successCount;
	private int skippedCount;
	private int failedCount;

}
