package com.deloitte.returns.entity.Gstr7;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@Table(name = "tax_pay", schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "igst", "sgst", "cgst", "cess", "liab_id", "trancd", "trandate" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_TaxPay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("debit_id")
	private String debitId;

	@JsonProperty("igst")
	@JsonPropertyDescription("IGST amount payable")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	public Gstr7_Igst igst;

	@JsonProperty("sgst")
	@JsonPropertyDescription("SGST amount payable")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	public Gstr7_Sgst sgst;

	@JsonProperty("cgst")
	@JsonPropertyDescription("CGST amount payable")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	public Gstr7_Cgst cgst;

	@JsonProperty("cess")
	@JsonPropertyDescription("Cess amount payable.")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	public Gstr7_Cess cess;

	@JsonProperty("liab_id")
	@JsonPropertyDescription("Liability identifier")
	@Column(name = "liab_id")
	public Double liabId;

	@JsonProperty("trancd")
	@Column(name = "trancd")
	public Double trancd;

	@JsonProperty("trandate")
	@JsonPropertyDescription("Transaction date")
	@Column(name = "trandate")

	public String trandate;

}
