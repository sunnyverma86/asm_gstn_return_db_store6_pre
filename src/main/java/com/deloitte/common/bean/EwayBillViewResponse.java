package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EwayBillViewResponse {

	private long ewaybillNo;
	private String ewaybillDate;

	private String frmGstin;
	private String frmName;
	private String frmState;

	private String toGstin;
	private String toName;
	private String toState;

	private String docNo;
	private String docDt;

	private double assessableValue;

	private double igst;
	private double cgst;
	private double sgst;
	private double cess;

	private String status;
}