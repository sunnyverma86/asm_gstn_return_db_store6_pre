package com.deloitte.tab.bean;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Gstr1B2BDto {

	private String gstin;
	private String returnPeriod;

	private String purchaserGstin;
	private String purchaserLegalName;
	private String purchaserTradeName;
	private String purchaserLocation;

	private String invoiceNo;
	private String invoiceDate;

	private BigDecimal invoiceValue;

	private BigDecimal rate;
	private BigDecimal taxableValue;

	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private BigDecimal cess;

	private String reverseCharge;
}