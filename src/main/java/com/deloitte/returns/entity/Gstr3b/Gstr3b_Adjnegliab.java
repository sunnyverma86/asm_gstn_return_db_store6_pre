
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "trans_typ", "tran_desc", "liab_ldg_id", "sgst", "cgst", "cess", "igst" })

@Entity
@Data
@Table(name = "adjnegliab", schema = "gstr3b")
public class Gstr3b_Adjnegliab implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@JsonProperty("tran_desc")
	@JsonPropertyDescription("Transaction description")

	@Column
	private String tranDesc;
	/**
	 * Liability Id
	 * 
	 */
	@JsonProperty("liab_ldg_id")
	@JsonPropertyDescription("Liability Id")
	@Column
	private double liabLdgId;

	@JsonProperty("sgst")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "sgst_id")
	private Gstr3b_Sgst sgst;
	@JsonProperty("cgst")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "cgst_id")
	private Gstr3b_Cgst cgst;
	@JsonProperty("cess")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "cess_id")
	private Gstr3b_Cess cess;
	@JsonProperty("igst")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "igst_id")
	private Gstr3b_Igst igst;

	private final static long serialVersionUID = -4384993488991065325L;

}
