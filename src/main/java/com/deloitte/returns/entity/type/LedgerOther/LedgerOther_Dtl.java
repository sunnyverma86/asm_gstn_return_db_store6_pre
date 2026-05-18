package com.deloitte.returns.entity.type.LedgerOther;

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
@Table(name = "dtl", schema = "ledger_other")
public class LedgerOther_Dtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dem_liab_id")
	private String demLiabID;

	@JsonProperty("desc")
	private String descDtl;

	@JsonProperty("dschrg_typ")
	private String dschrgTyp;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("ref_no")
	private String refNo;

	@JsonProperty("stayStatus")
	private String stayStatus;

	@JsonProperty("tr_typ")
	private String trTyp;

	@JsonProperty("tx_prd_frm")
	private String txPrdFrm;

	@JsonProperty("tx_prd_to")
	private String txPrdTo;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private LedgerOther_Cess cess;

	@JsonProperty("cessbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cessbal_id")
	private LedgerOther_Cessbal cessbal;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private LedgerOther_Cgst cgst;

	@JsonProperty("cgstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgstbal_id")
	private LedgerOther_Cgstbal cgstbal;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private LedgerOther_Igst igst;

	@JsonProperty("igstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igstbal_id")
	private LedgerOther_Igstbal igstbal;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private LedgerOther_Sgst sgst;

	@JsonProperty("sgstbal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgstbal_id")
	private LedgerOther_Sgstbal sgstbal;

}