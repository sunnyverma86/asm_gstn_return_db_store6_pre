package com.deloitte.returns.entity.type.LedgerLiability;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "op_bal", schema = "ledger_liability")
public class LedgerLiability_OpBAL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("desc")
	private String desc_data;

	@JsonProperty("sgstTaxAmt")
	private long sgstTaxAmt;

	@JsonProperty("cgstTaxAmt")
	private long cgstTaxAmt;

	@JsonProperty("igstTaxAmt")
	private long igstTaxAmt;

	@JsonProperty("cessTaxAmt")
	private long cessTaxAmt;

	@JsonProperty("bal")
	private long bal;

	@JsonProperty("sl_no")
	private long slNo;

	@JsonProperty("tr_typ")
	private String trTyp;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private LedgerLiability_Igst igst;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private LedgerLiability_Cgst cgst;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private LedgerLiability_Sgst sgst;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private LedgerLiability_Cess cess;

	@JsonProperty("cl_tot")
	private Long clTot;

	@JsonProperty("op_tot")
	private Long opTot;

	@JsonProperty("ref_no")
	private String refNos;

	@JsonProperty("refNo")
	private String refNo;

	@JsonProperty("dpt_dt")
	private String dptDt;

	@JsonProperty("ret_period")
	private String retPeriod;

	@JsonProperty("rpt_dt")
	private String rptDt;

	@JsonProperty("dpt_time")
	private String dptTime;

	@JsonProperty("tot_rng_bal")
	private long totRngBal;

	//
	@JsonProperty("igstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igstbal_id")
	private LedgerLiability_IgstBal igstbal;

	@JsonProperty("cgstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgstbal_id")
	private LedgerLiability_CgstBal cgstbal;

	@JsonProperty("sgstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgstbal_id")
	private LedgerLiability_SgstBal sgstbal;

	@JsonProperty("cessbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cessbal_id")
	private LedgerLiability_CessBal cessbal;
	
	@JsonProperty("dschrg_typ")
    private String dschrgTyp;

}
