package com.deloitte.returns.entity.Gstr8;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstr9Iamt", "gstr9Samt", "gstr9Camt", "liab_id", "debit_id", "trancd", "trandate" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "tax_pay", schema = "gstr8")
@Data
public class Gstr8_TaxPay implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * IGST amount payable
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST amount payable")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "iamt_id")
	public Gstr8_Iamt iamt;

	/**
	 * SGST amount payable
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST amount payable")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "samt_id")
	public Gstr8_Samt samt;

	/**
	 * CGST amount payable
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST amount payable")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "camt_id")
	public Gstr8_Camt camt;

	/**
	 * Liability identifier
	 * 
	 */
	@JsonProperty("liab_id")
	@JsonPropertyDescription("Liability identifier")
	@Column
	public Double liabId;

	/**
	 * Debit number
	 * 
	 */
	@JsonProperty("debit_id")
	@JsonPropertyDescription("Debit number")
	@Column
	public String debitId;

	@JsonProperty("trancd")
	@Column
	public Double trancd;

	/**
	 * Transaction date
	 * 
	 */
	@JsonProperty("trandate")
	@JsonPropertyDescription("Transaction date")
	@Column
	public String trandate;

	private final static long serialVersionUID = -2266856425926149660L;

}
