
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
@JsonPropertyOrder({ "cess", "cgst", "igst", "rt", "sgst", "txval" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "gstr5Liab", schema = "gstr5")
@Data
public class Gstr5_Liab implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * CESS Amount (Required)
	 * 
	 */
	@JsonProperty("cess")
	@JsonPropertyDescription("CESS Amount")
	@NotNull
	@Column
	public Double cess;
	/**
	 * CGST Amount (Required)
	 * 
	 */
	@JsonProperty("cgst")
	@JsonPropertyDescription("CGST Amount")
	@NotNull
	@Column
	public Double cgst;
	/**
	 * IGST Amount (Required)
	 * 
	 */
	@JsonProperty("igst")
	@JsonPropertyDescription("IGST Amount")
	@NotNull
	@Column
	public Double igst;
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
	 * SGST Amount (Required)
	 * 
	 */
	@JsonProperty("sgst")
	@JsonPropertyDescription("SGST Amount")
	@NotNull
	@Column
	public Double sgst;
	/**
	 * Taxable value as per invoice (Required)
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable value as per invoice")
	@NotNull
	@Column
	public Double txval;

	private final static long serialVersionUID = 1868598793306470797L;

}
