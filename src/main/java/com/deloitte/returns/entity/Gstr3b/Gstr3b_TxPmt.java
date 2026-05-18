
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tx_py", "adjnegliab", "nettaxpay", "pdcash", "pditc", "pdnls", "s_py" })

@Entity
@Data
@Table(name = "tx_pmt", schema = "gstr3b")
public class Gstr3b_TxPmt implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tx_py")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_TxPy> txPy = new ArrayList<Gstr3b_TxPy>();
	/**
	 * Adjusted tax payable amount
	 * 
	 */
	@JsonProperty("adjnegliab")
	@JsonPropertyDescription("Adjusted tax payable amount")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_Adjnegliab> adjnegliab = new ArrayList<Gstr3b_Adjnegliab>();
	/**
	 * Net tax payable amount
	 * 
	 */
	@JsonProperty("nettaxpay")
	@JsonPropertyDescription("Net tax payable amount")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_Nettaxpay> nettaxpay = new ArrayList<Gstr3b_Nettaxpay>();
	/**
	 * Paid Cash
	 * 
	 */
	@JsonProperty("pdcash")
	@JsonPropertyDescription("Paid Cash")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_Pdcash> pdcash = new ArrayList<Gstr3b_Pdcash>();
	/**
	 * Paid ITC
	 * 
	 */
	@JsonProperty("pditc")
	@JsonPropertyDescription("Paid ITC")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "pditc_id")
	private Gstr3b_Pditc pditc;
	/**
	 * Paid NLS
	 * 
	 */
	@JsonProperty("pdnls")
	@JsonPropertyDescription("Paid NLS")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_Pdnls> pdnls;
	/**
	 * SGST Payable
	 * 
	 */
	@JsonProperty("s_py")
	@JsonPropertyDescription("SGST Payable")
	@Column
	private double sPy;

	private final static long serialVersionUID = 3882917223072523133L;

	@JsonProperty("net_tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<Gstr3b_Nettaxpay> netTaxPay;

}
