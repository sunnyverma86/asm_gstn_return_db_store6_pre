package com.deloitte.returns.entity.Payment;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "otc", schema = "payment")


public class Payment_Otc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@JsonProperty("cess_pnlty")
	private String cess_pnlty;
	
	@JsonProperty("br_name")
	private String br_name;
	
	@JsonProperty("instrument_ty")
	private String instrument_ty;
	
	@JsonProperty("cess_tax")
	private String cess_tax;
	
	@JsonProperty("cgst_intr")
	private String cgst_intr;
	
	@JsonProperty("cin")
	private String cin;

	
	@JsonProperty("instrument_num")
	private String instrument_num;
	
	@JsonProperty("sgst_total")
	private String sgst_total;
	
	@JsonProperty("payment_dt")
	private String payment_dt;
	
	@JsonProperty("sgst_intr")
	private String sgst_intr;
	
	@JsonProperty("sgst_oth")
	private String sgst_oth;
	
	@JsonProperty("gstin")
	private String gstin;
	
	
	@JsonProperty("cgst_fee")
	private String cgst_fee;
	
	@JsonProperty("cess_oth")
	private String cess_oth;
	
	@JsonProperty("igst_fee")
	private String igst_fee;
	
	@JsonProperty("igst_total")
	private String igst_total;
	
	@JsonProperty("total_amt")
	private String total_amt;
	
	@JsonProperty("sgst_pnlty")
	private String sgst_pnlty;
	
	@JsonProperty("reporting_tim")
	private String reporting_tim;
	
	@JsonProperty("igst_intr")
	private String igst_intr;
	
	@JsonProperty("cgst_total")
	private String cgst_total;
	
	@JsonProperty("cgst_pnlty")
	private String cgst_pnlty;
	
	@JsonProperty("bank_ref_num")
	private String bank_ref_num;
	
	@JsonProperty("cpin_tim")
	private String cpin_tim;
	
	@JsonProperty("igst_tax")
	private String igst_tax;
	
	@JsonProperty("cess_fee")
	private String cess_fee;
	
	@JsonProperty("br_ifsc_cd")
	private String br_ifsc_cd;
	
	@JsonProperty("cess_intr")
	private String cess_intr;
	
	@JsonProperty("reporting_dt")
	private String reporting_dt;
	
	@JsonProperty("igst_oth")
	private String igst_oth;
	
	@JsonProperty("sgst_fee")
	private String sgst_fee;
	
	@JsonProperty("pymnt_ack_tim")
	private String pymnt_ack_tim;
	
	@JsonProperty("cgst_oth")
	private String cgst_oth;
	
	
	@JsonProperty("cess_total")
	private String cess_total;
	
	@JsonProperty("payment_tim")
	private String payment_tim;
	
	@JsonProperty("ack_num")
	private String ack_num;
	
	@JsonProperty("bank_cd")
	private String bank_cd;
	
	@JsonProperty("igst_pnlty")
	private String igst_pnlty;
	
	
	@JsonProperty("br_location")
	private String br_location;
	
	@JsonProperty("cgst_tax")
	private String cgst_tax;
	
	
	@JsonProperty("sgst_tax")
	private String sgst_tax;
	
	@JsonProperty("pymnt_ack_dt")
	private String pymnt_ack_dt;
	
	@JsonProperty("instrument_micr_cd")
	private String instrument_micr_cd;
	
	@JsonProperty("cpin")
	private String cpin;
	
	@JsonProperty("cpin_dt")
	private String cpin_dt;
	
	@JsonProperty("status")
	private String status;

}
