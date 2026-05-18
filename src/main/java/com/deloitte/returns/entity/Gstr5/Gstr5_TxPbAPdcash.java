
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ipd", "cpd", "spd", "cspd", "ipddn", "cpddn", "spddn", "cspddn" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "tx_pb_a_pdcash", schema = "gstr5")
@Data
public class Gstr5_TxPbAPdcash implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * IGST Paid (Required)
	 * 
	 */
	@JsonProperty("ipd")
	@JsonPropertyDescription("IGST Paid")
	@NotNull
	@Column
	public Double ipd;
	/**
	 * CGST Paid (Required)
	 * 
	 */
	@JsonProperty("cpd")
	@JsonPropertyDescription("CGST Paid")
	@NotNull
	@Column
	public Double cpd;
	/**
	 * SGST Paid (Required)
	 * 
	 */
	@JsonProperty("spd")
	@JsonPropertyDescription("SGST Paid")
	@NotNull
	@Column
	public Double spd;
	/**
	 * CESS Paid (Required)
	 * 
	 */
	@JsonProperty("cspd")
	@JsonPropertyDescription("CESS Paid")
	@NotNull
	@Column
	public Double cspd;
	/**
	 * IGST Paid Debit Number
	 * 
	 */
	@JsonProperty("ipddn")
	@JsonPropertyDescription("IGST Paid Debit Number")
	@Column
	public String ipddn;
	/**
	 * CGST Paid Debit Number
	 * 
	 */
	@JsonProperty("cpddn")
	@JsonPropertyDescription("CGST Paid Debit Number")
	@Column
	public String cpddn;
	/**
	 * SGST Paid Debit Number
	 * 
	 */
	@JsonProperty("spddn")
	@JsonPropertyDescription("SGST Paid Debit Number")
	@Column
	public String spddn;
	/**
	 * CESS Paid Debit Number
	 * 
	 */
	@JsonProperty("cspddn")
	@JsonPropertyDescription("CESS Paid Debit Number")
	@Column
	public String cspddn;
	private final static long serialVersionUID = -4049909590735131425L;

}
