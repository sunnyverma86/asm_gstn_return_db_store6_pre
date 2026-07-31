package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldComparison {

	private String field;

	private String newValue;

	private String oldValue;

	private boolean matched;

}
