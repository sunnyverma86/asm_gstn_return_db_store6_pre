package com.deloitte.returns.entity.Forest.Model;

import java.util.List;

import lombok.Data;

@Data
public class ForestApiResponse {

	private String status;
	private int page;
	private int per_page;
	private int returned;
	private boolean has_more;

	private List<ForestPaymentDto> response;
}
