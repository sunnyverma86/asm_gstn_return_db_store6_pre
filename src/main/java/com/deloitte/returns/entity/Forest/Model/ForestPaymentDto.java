package com.deloitte.returns.entity.Forest.Model;

import lombok.Data;

@Data
public class ForestPaymentDto {

	private String modulename;
	private String stakeholder;
	private String mineral_name;
	private String pan;
	private String gst_no;
	private String dfo_gstno;
	private String payment_type;
	private String amount;
	private String payment_date;
	private String cpin;
}