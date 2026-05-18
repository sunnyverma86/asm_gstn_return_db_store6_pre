
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
@JsonPropertyOrder({ "chksum", "csamt", "camt", "samt", "iamt", "pos", "diff_percent", "rt", "supty", "txval" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "b2c", schema = "gstr5")
@Data
public class Gstr5_B2c implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Checksum Value (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@JsonPropertyDescription("Checksum Value")
	@NotNull
	@Column
	public String chksum;
	/**
	 * CESS Amount As Per Invoice
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("CESS Amount As Per Invoice")
	@Column
	public Double csamt;
	/**
	 * CGST Amount As Per Invoice
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST Amount As Per Invoice")
	@Column
	public Double camt;
	/**
	 * SGST Amount As Per Invoice
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST Amount As Per Invoice")
	@Column
	public Double samt;
	/**
	 * IGST Amount As Per Invoice
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST Amount As Per Invoice")
	@Column
	public Double iamt;
	/**
	 * Place Of Supply
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("Place Of Supply")
	@Column
	public String pos;
	/**
	 * Differential percentage
	 * 
	 */
	@JsonProperty("diff_percent")
	@JsonPropertyDescription("Differential percentage")
	@Column
	public Double diffPercent;
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
	 * Supply Type (Required)
	 * 
	 */
	@JsonProperty("supty")
	@JsonPropertyDescription("Supply Type")
	@NotNull
	@Column
	public String supty;
	/**
	 * Taxable Value As Per Invoice (Required)
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable Value As Per Invoice")
	@NotNull
	@Column
	public Double txval;

	private final static long serialVersionUID = -7692010739755557418L;

}
