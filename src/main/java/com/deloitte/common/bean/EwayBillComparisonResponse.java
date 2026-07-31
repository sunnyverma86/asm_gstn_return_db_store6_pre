package com.deloitte.common.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EwayBillComparisonResponse {

	private EwayBillViewResponse newData;

	private EwayBillViewResponse oldData;

	private List<FieldComparison> comparisons;

}
