
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
@JsonPropertyOrder({ "i_pdi", "i_pdc", "i_pds", "cs_pdcs", "i_pdidn", "i_pdcdn", "i_pdsdn", "cs_pdcsdn" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "pdcr", schema = "gstr5")
@Data
public class Gstr5_Pdcr implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Igst paid as Igst (Required)
	 * 
	 */
	@JsonProperty("i_pdi")
	@JsonPropertyDescription("Igst paid as Igst")
	@NotNull
	@Column
	public Double iPdi;
	/**
	 * Igst paid as Cgst (Required)
	 * 
	 */
	@JsonProperty("i_pdc")
	@JsonPropertyDescription("Igst paid as Cgst")
	@NotNull
	@Column
	public Double iPdc;
	/**
	 * Igst paid as Sgst (Required)
	 * 
	 */
	@JsonProperty("i_pds")
	@JsonPropertyDescription("Igst paid as Sgst")
	@NotNull
	@Column
	public Double iPds;
	/**
	 * Cess paid as Cess (Required)
	 * 
	 */
	@JsonProperty("cs_pdcs")
	@JsonPropertyDescription("Cess paid as Cess")
	@NotNull
	@Column
	public Double csPdcs;
	/**
	 * Igst paid as Igst Debit Number
	 * 
	 */
	@JsonProperty("i_pdidn")
	@JsonPropertyDescription("Igst paid as Igst Debit Number")
	@Column
	public String iPdidn;
	/**
	 * Igst paid as Cgst Debit Number
	 * 
	 */
	@JsonProperty("i_pdcdn")
	@JsonPropertyDescription("Igst paid as Cgst Debit Number")
	@Column
	public String iPdcdn;
	/**
	 * Igst paid as Sgst Debit Number
	 * 
	 */
	@JsonProperty("i_pdsdn")
	@JsonPropertyDescription("Igst paid as Sgst Debit Number")
	@Column
	public String iPdsdn;
	/**
	 * Cess paid as Cess Debit Number
	 * 
	 */
	@JsonProperty("cs_pdcsdn")
	@JsonPropertyDescription("Cess paid as Cess Debit Number")
	@Column
	public String csPdcsdn;
	private final static long serialVersionUID = -493471206365783547L;

}
