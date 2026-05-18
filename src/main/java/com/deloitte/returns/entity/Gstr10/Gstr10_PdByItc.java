package com.deloitte.returns.entity.Gstr10;

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
@Table(name = "pd_by_itc", schema = "gstr10")
@Entity
public class Gstr10_PdByItc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("igst_igst_amt")
	private long igstIgstAmt;

	@JsonProperty("cess_cess_amt")
	private long cessCessAmt;

	@JsonProperty("cgst_igst_amt")
	private long cgstIgstAmt;

	@JsonProperty("cgst_cgst_amt")
	private long cgstCgstAmt;

	@JsonProperty("trandate")
	private String trandate;

	@JsonProperty("sgst_igst_amt")
	private long sgstIgstAmt;

	@JsonProperty("trancd")
	private long trancd;

	@JsonProperty("igst_sgst_amt")
	private long igstSgstAmt;

	@JsonProperty("sgst_sgst_amt")
	private long sgstSgstAmt;

	@JsonProperty("igst_cgst_amt")
	private long igstCgstAmt;

	@JsonProperty("debit_id")
	private String debitID;

	@JsonProperty("liab_id")
	private long liabID;

}
