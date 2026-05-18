
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
 * Paid NLS
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "liab_ldg_id", "trans_typ", "trans_desc", "ipd", "cpd", "spd", "cspd" })

@Entity
@Data
@Table(name = "pdnls", schema = "gstr3b")
public class Gstr3b_Pdnls implements Serializable {
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
	 * Transaction description
	 * 
	 */
	@JsonProperty("trans_desc")
	@JsonPropertyDescription("Transaction description")
	@Column
	private String transDesc;
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

	private final static long serialVersionUID = 3191755157131881511L;

}
