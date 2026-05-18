
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
@JsonPropertyOrder({ "camt", "csamt", "iamt", "rt", "samt", "txval" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "itm2", schema = "gstr5")
@Data
public class Gstr5_Itm2 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * CGST Amount as per invoice
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST Amount as per invoice")
	@Column
	public Double camt;
	/**
	 * CESS Amount as per invoice
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("CESS Amount as per invoice")
	@Column
	public Double csamt;
	/**
	 * IGST Amount as per invoice
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST Amount as per invoice")
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
	 * SGST Amount as per invoice
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST Amount as per invoice")
	@Column
	public Double samt;
	/**
	 * Taxable value as per invoice (Required)
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable value as per invoice")
	@NotNull
	@Column
	public Double txval;

	private final static long serialVersionUID = -2934290775548742192L;

}
