
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
@JsonPropertyOrder({ "rt", "txval", "iamt", "camt", "samt", "csamt" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "revchrg", schema = "gstr5")
@Data
public class Gstr5_Revchrg implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Tax rate (Required)
	 * 
	 */
	@JsonProperty("rt")
	@JsonPropertyDescription("Tax rate")
	@NotNull
	@Column
	public Double rt;
	/**
	 * Taxable value as per invoice (Required)
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable value as per invoice")
	@NotNull
	@Column
	public Double txval;
	/**
	 * IGST Amount as per invoice (Required)
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST Amount as per invoice")
	@NotNull
	@Column
	public Double iamt;
	/**
	 * CGST Amount as per invoice (Required)
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST Amount as per invoice")
	@NotNull
	@Column
	public Double camt;
	/**
	 * SGST Amount as per invoice (Required)
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST Amount as per invoice")
	@NotNull
	@Column
	public Double samt;
	/**
	 * CESS Amount as per invoice (Required)
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("CESS Amount as per invoice")
	@NotNull
	@Column
	public Double csamt;

	private final static long serialVersionUID = 1118417265055798189L;

}
