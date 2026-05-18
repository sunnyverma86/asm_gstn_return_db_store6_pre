
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "liab_ldg_id", "trans_typ", "ipd", "cpd", "spd", "cspd", "i_intrpd", "c_intrpd", "s_intrpd",
		"cs_intrpd", "c_lfeepd", "s_lfeepd" })
@Entity
@Data
@Table(name = "pdcash", schema = "gstr3b")
public class Gstr3b_Pdcash implements Serializable {

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
	 * Transaction Type
	 * 
	 */
	@JsonProperty("trans_typ")
	@JsonPropertyDescription("Transaction Type")
	@Column
	private double transTyp;
	/**
	 * IGST Paid
	 * 
	 */
	@JsonProperty("ipd")
	@JsonPropertyDescription("IGST Paid")
	@Column
	private double ipd;
	/**
	 * CGST Paid
	 * 
	 */
	@JsonProperty("cpd")
	@JsonPropertyDescription("CGST Paid")
	@Column
	private double cpd;
	/**
	 * SGST Paid
	 * 
	 */
	@JsonProperty("spd")
	@JsonPropertyDescription("SGST Paid")
	@Column
	private double spd;
	/**
	 * CESS Paid
	 * 
	 */
	@JsonProperty("cspd")
	@JsonPropertyDescription("CESS Paid")
	@Column
	private double cspd;
	/**
	 * IGST INTEREST Paid
	 * 
	 */
	@JsonProperty("i_intrpd")
	@JsonPropertyDescription("IGST INTEREST Paid")
	@Column
	private double iIntrpd;
	/**
	 * CGST INTEREST Paid
	 * 
	 */
	@JsonProperty("c_intrpd")
	@JsonPropertyDescription("CGST INTEREST Paid")
	@Column
	private double cIntrpd;
	/**
	 * SGST INTEREST Paid
	 * 
	 */
	@JsonProperty("s_intrpd")
	@JsonPropertyDescription("SGST INTEREST Paid")
	@Column
	private double sIntrpd;
	/**
	 * CESS INTEREST Paid
	 * 
	 */
	@JsonProperty("cs_intrpd")
	@JsonPropertyDescription("CESS INTEREST Paid")
	@Column
	private double csIntrpd;
	/**
	 * CGST LateFee Paid
	 * 
	 */
	@JsonProperty("c_lfeepd")
	@JsonPropertyDescription("CGST LateFee Paid")
	@Column
	private double cLfeepd;
	/**
	 * SGST LateFee Paid
	 * 
	 */
	@JsonProperty("s_lfeepd")
	@JsonPropertyDescription("SGST LateFee Paid")
	@Column
	private double sLfeepd;

	/**
	 * IGST LateFee Paid
	 *
	 */
	@JsonProperty("i_lfeepd")
	@JsonPropertyDescription("IGST LateFee Paid")
	@Column
	private double iLfeepd;

	/**
	 * CESS LateFee Paid
	 *
	 */
	@JsonProperty("cs_lfeepd")
	@JsonPropertyDescription("CESS LateFee Paid")
	@Column
	private double csLfeepd;
	@JsonProperty("iamt")
	@Column
	private double iamt;
	@JsonProperty("camt")
	@Column
	private double camt;
	@JsonProperty("samt")
	@Column
	private double samt;
	@JsonProperty("csamt")
	@Column
	private double csamt;

	private final static long serialVersionUID = -8214332548923402709L;

}
