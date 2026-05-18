
package com.deloitte.returns.entity.Gstr3b;

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

/**
 * Paid ITC
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "liab_ldg_id", "trans_typ", "i_pdi", "i_pdc", "i_pds", "c_pdi", "c_pdc", "s_pdi", "s_pds",
		"cs_pdcs" })

@Entity
@Data
@Table(name = "pditc", schema = "gstr3b")
public class Gstr3b_Pditc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Liability Id
	 * 
	 */
	@JsonProperty("liab_ldg_id")
	@JsonPropertyDescription("Liability Id")
	@Column
	private double liabLdgId;
	/**
	 * Transaction Id
	 * 
	 */
	@JsonProperty("trans_typ")
	@JsonPropertyDescription("Transaction Id")
	@Column
	private double transTyp;
	/**
	 * IGST Paid as IGST
	 * 
	 */
	@JsonProperty("i_pdi")
	@JsonPropertyDescription("IGST Paid as IGST")
	@Column
	private double iPdi;
	/**
	 * IGST Paid as CGST
	 * 
	 */
	@JsonProperty("i_pdc")
	@JsonPropertyDescription("IGST Paid as CGST")
	@Column
	private double iPdc;
	/**
	 * IGST Paid as SGST
	 * 
	 */
	@JsonProperty("i_pds")
	@JsonPropertyDescription("IGST Paid as SGST")
	@Column
	private double iPds;
	/**
	 * CGST Paid as IGST
	 * 
	 */
	@JsonProperty("c_pdi")
	@JsonPropertyDescription("CGST Paid as IGST")
	@Column
	private double cPdi;
	/**
	 * CGST Paid as CGST
	 * 
	 */
	@JsonProperty("c_pdc")
	@JsonPropertyDescription("CGST Paid as CGST")
	@Column
	private double cPdc;
	/**
	 * SGST Paid as IGST
	 * 
	 */
	@JsonProperty("s_pdi")
	@JsonPropertyDescription("SGST Paid as IGST")
	@Column
	private double sPdi;
	/**
	 * SGST Paid as SGST
	 * 
	 */
	@JsonProperty("s_pds")
	@JsonPropertyDescription("SGST Paid as SGST")
	@Column
	private double sPds;
	/**
	 * CESS Paid as CESS
	 * 
	 */
	@JsonProperty("cs_pdcs")
	@JsonPropertyDescription("CESS Paid as CESS")
	@Column
	private double csPdcs;

	private final static long serialVersionUID = -6720127809897772374L;

}
