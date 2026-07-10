package com.deloitte.tab.bean;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Gstr3BMonthlyDto {

	private String month;
	private String filingDate;

	private BigDecimal turnover;

	// TAX LIABILITY
	private BigDecimal igstLiability;
	private BigDecimal cgstLiability;
	private BigDecimal sgstLiability;
	private BigDecimal cessLiability;

	// REVERSE CHARGE
	private BigDecimal igstReverseCharge;
	private BigDecimal cgstReverseCharge;
	private BigDecimal sgstReverseCharge;

	// ITC REVERSE
	private BigDecimal igstItcReverse;
	private BigDecimal cgstItcReverse;
	private BigDecimal sgstItcReverse;
	
	// ITC AVAILED
	private BigDecimal igstItcAvailed;
	private BigDecimal cgstItcAvailed;
	private BigDecimal sgstItcAvailed;

	// NET ITC AVAILABLE
	private BigDecimal igstNetItc;
	private BigDecimal cgstNetItc;
	private BigDecimal sgstNetItc;
	private BigDecimal cessNetItc;

	// TAX PAID IN CASH RC
	private BigDecimal igstRcCash;
	private BigDecimal cgstRcCash;
	private BigDecimal sgstRcCash;
	private BigDecimal cessRcCash;

	// PAYMENT THROUGH ITC
	private BigDecimal igstItc;
	private BigDecimal cgstItc;
	private BigDecimal sgstItc;
	private BigDecimal cessItc;

	private BigDecimal totalItcTax;
	private BigDecimal totalItcInterest;
	private BigDecimal totalItcLateFee;

	// CASH PAYMENT
	private BigDecimal cashIgst;
	private BigDecimal cashCgst;
	private BigDecimal cashSgst;
	private BigDecimal cashCess;

	// REVERSE CHARGE
	private BigDecimal igstCash;
	private BigDecimal cgstCash;
	private BigDecimal sgstCash;

	private BigDecimal totalCashTax;
	private BigDecimal totalCashInterest;
	private BigDecimal totalCashLateFee;

	// TOTALS
	private BigDecimal taxLiability;
	private BigDecimal reverseCharge;
	private BigDecimal itcReverse;
	private BigDecimal netItcAvailable;

	private BigDecimal interest;
	private BigDecimal lateFee;

	// PAYMENT IGST

	private BigDecimal igstInterest;
	private BigDecimal igstLateFee;

	// PAYMENT CGST

	private BigDecimal cgstInterest;
	private BigDecimal cgstLateFee;

	// PAYMENT SGST

	private BigDecimal sgstInterest;
	private BigDecimal sgstLateFee;
}