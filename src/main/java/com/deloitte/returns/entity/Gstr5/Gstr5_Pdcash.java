
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
@JsonPropertyOrder({ "i_pdint", "c_pdint", "s_pdint", "cs_pdint", "i_pdintdn", "c_pdintdn", "s_pdintdn", "cs_pdintdn",
		"s_pdlfee", "c_pdlfee", "s_pdlfeedn", "c_pdlfeedn" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "pdcash", schema = "gstr5")
@Data
public class Gstr5_Pdcash implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Igst Paid as Intrest (Required)
	 * 
	 */
	@JsonProperty("i_pdint")
	@JsonPropertyDescription("Igst Paid as Intrest")
	@NotNull
	@Column
	public Double iPdint;
	/**
	 * Cgst Paid as Intrest (Required)
	 * 
	 */
	@JsonProperty("c_pdint")
	@JsonPropertyDescription("Cgst Paid as Intrest")
	@NotNull
	@Column
	public Double cPdint;
	/**
	 * Sgst Paid as Intrest (Required)
	 * 
	 */
	@JsonProperty("s_pdint")
	@JsonPropertyDescription("Sgst Paid as Intrest")
	@NotNull
	@Column
	public Double sPdint;
	/**
	 * Cess Paid as Intrest (Required)
	 * 
	 */
	@JsonProperty("cs_pdint")
	@JsonPropertyDescription("Cess Paid as Intrest")
	@NotNull
	@Column
	public Double csPdint;
	/**
	 * Igst Paid as Intrest Debit Number
	 * 
	 */
	@JsonProperty("i_pdintdn")
	@JsonPropertyDescription("Igst Paid as Intrest Debit Number")
	@Column
	public String iPdintdn;
	/**
	 * Cgst Paid as Intrest Debit Number
	 * 
	 */
	@JsonProperty("c_pdintdn")
	@JsonPropertyDescription("Cgst Paid as Intrest Debit Number")
	@Column
	public String cPdintdn;
	/**
	 * Sgst Paid as Intrest Debit Number
	 * 
	 */
	@JsonProperty("s_pdintdn")
	@JsonPropertyDescription("Sgst Paid as Intrest Debit Number")
	@Column
	public String sPdintdn;
	/**
	 * Cess Paid as Intrest Debit Number
	 * 
	 */
	@JsonProperty("cs_pdintdn")
	@JsonPropertyDescription("Cess Paid as Intrest Debit Number")
	@Column
	public String csPdintdn;
	/**
	 * Sgst Paid as Iate fee (Required)
	 * 
	 */
	@JsonProperty("s_pdlfee")
	@JsonPropertyDescription("Sgst Paid as Iate fee")
	@NotNull
	@Column
	public Double sPdlfee;
	/**
	 * Cgst Paid as Iate fee (Required)
	 * 
	 */
	@JsonProperty("c_pdlfee")
	@JsonPropertyDescription("Cgst Paid as Iate fee")
	@NotNull
	@Column
	public Double cPdlfee;
	/**
	 * Sgst Paid as Iate fee Debit Number (Required)
	 * 
	 */
	@JsonProperty("s_pdlfeedn")
	@JsonPropertyDescription("Sgst Paid as Iate fee Debit Number")
	@NotNull
	@Column
	public String sPdlfeedn;
	/**
	 * Cgst Paid as Iate fee Debit Number
	 * 
	 */
	@JsonProperty("c_pdlfeedn")
	@JsonPropertyDescription("Cgst Paid as Iate fee Debit Number")
	@Column
	public String cPdlfeedn;

	private final static long serialVersionUID = -5029229579627009016L;

}
