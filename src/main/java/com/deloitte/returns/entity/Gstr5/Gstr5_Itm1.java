
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
@JsonPropertyOrder({ "csamt", "elg", "iamt", "rt", "tx_cs", "tx_i", "txval" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "itm1", schema = "gstr5")
@Data
public class Gstr5_Itm1 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * CESS amount (Required)
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("CESS amount")
	@NotNull
	@Column
	public Double csamt;
	/**
	 * Eligibility of ITC (Required)
	 * 
	 */
	@JsonProperty("elg")
	@JsonPropertyDescription("Eligibility of ITC")
	@NotNull
	@Column
	public String elg;
	/**
	 * IGST amount (Required)
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST amount")
	@NotNull
	@Column
	public Double iamt;
	/**
	 * Tax Rate (Required)
	 * 
	 */
	@JsonProperty("rt")
	@JsonPropertyDescription("Tax Rate")
	@NotNull
	@Column
	public Double rt;
	/**
	 * Total CESS available as ITC (Required)
	 * 
	 */
	@JsonProperty("tx_cs")
	@JsonPropertyDescription("Total CESS available as ITC")
	@NotNull
	@Column
	public Double txCs;
	/**
	 * Total IGST available as ITC (Required)
	 * 
	 */
	@JsonProperty("tx_i")
	@JsonPropertyDescription("Total IGST available as ITC")
	@NotNull
	@Column
	public Double txI;
	/**
	 * Taxable value as per invoice (Required)
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable value as per invoice")
	@NotNull
	@Column
	public Double txval;

	private final static long serialVersionUID = 3591685394840669336L;

}
