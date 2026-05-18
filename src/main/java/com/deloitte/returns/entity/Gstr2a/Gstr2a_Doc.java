
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "camt", "chksum", "aspd", "atyp", "csamt", "odocdt", "odocnum", "docdt", "docnum", "iamt",
		"isd_docty", "itc_elg", "samt" })

@Entity
@Table(name = "doc", schema = "gstr2a")
@Data
public class Gstr2a_Doc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	@Column
	public Double camt;
	@JsonProperty("chksum")
	@Column
	public String chksum;
	/**
	 * Original period in which invoice was added
	 * 
	 */
	@JsonProperty("aspd")
	@JsonPropertyDescription("Original period in which invoice was added")
	@Column
	public String aspd;
	/**
	 * flag to determine type of amendment
	 * 
	 */
	@JsonProperty("atyp")
	@JsonPropertyDescription("flag to determine type of amendment")
	@Column
	public String atyp;
	@JsonProperty("csamt")
	@Column
	public Double csamt;
	@JsonProperty("cess")
	@Column
	public Double cess;
	@JsonProperty("odocdt")
	@Column
	public String odocdt;
	/**
	 * Revised Document number
	 * 
	 */
	@JsonProperty("odocnum")
	@JsonPropertyDescription(" Revised Document number")
	@Column
	public String odocnum;
	@JsonProperty("docdt")
	@Column
	public String docdt;
	/**
	 * Document number
	 * 
	 */
	@JsonProperty("docnum")
	@JsonPropertyDescription("Document number")
	@Column
	public String docnum;
	@JsonProperty("iamt")
	@Column
	public Double iamt;
	/**
	 * document type
	 * 
	 */
	@JsonProperty("isd_docty")
	@JsonPropertyDescription("document type")
	@Column
	public String isdDocty;
	/**
	 * Eligible for ITC
	 * 
	 */
	@JsonProperty("itc_elg")
	@JsonPropertyDescription("Eligible for ITC")
	@Column
	public String itcElg;
	@JsonProperty("samt")
	@Column
	public Double samt;

	private final static long serialVersionUID = 5392851843939293476L;

}
