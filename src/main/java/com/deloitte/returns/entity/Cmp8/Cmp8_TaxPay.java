package com.deloitte.returns.entity.Cmp8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tax_pay", schema = "cmp08")
@JsonIgnoreProperties
public class Cmp8_TaxPay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//
	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Cmp8_Sgst sgst;

	@JsonProperty("trandate")
	private String trandate;

	@JsonProperty("trancd")
	private long trancd;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Cmp8_Cgst cgst;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private Cmp8_Cess cess;

	@JsonProperty("debit_id")
	private String debitID;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private Cmp8_Igst igst;

	@JsonProperty("liab_id")
	private long liabID;
	//
	//
	@JsonProperty("csamt")
	private long csamt;

	@JsonProperty("samt")
	private long samt;

	@JsonProperty("camt")
	private long camt;

	@JsonProperty("tax_val")
	private Long taxVal;

	@JsonProperty("iamt")
	private long iamt;
	//

}