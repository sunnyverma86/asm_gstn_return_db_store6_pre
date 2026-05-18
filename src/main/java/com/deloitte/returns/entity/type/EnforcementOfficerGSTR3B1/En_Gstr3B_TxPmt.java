package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tx_pmt", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_TxPmt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tx_py")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<En_Gstr3B_TxPy> txPy;

	@JsonProperty("net_tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<En_Gstr3B_NetTaxPay> netTaxPay;

	@JsonProperty("adjnegliab")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<En_Gstr3B_Adjnegliab> adjnegliab;

	@JsonProperty("pdnls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<En_Gstr3B_Pdnls> pdnls;

	@JsonProperty("pdcash")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	private List<En_Gstr3B_Pdcash> pdcash;

	@JsonProperty("pditc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pditc_id")
	private En_Gstr3B_Pditc pditc;


}