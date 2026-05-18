package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table5", schema = "gstr9")
@Entity
public class Gstr9_Table5 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("amd_neg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amd_neg_id")
	private Gstr9_5AMDNeg amdNeg;

	@JsonProperty("amd_pos")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amd_pos_id")
	private Gstr9_AMDPos amdPos;

	@JsonProperty("cr_nt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_nt_id")
	private Gstr9_CRNT crNT;

	@JsonProperty("dr_nt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dr_nt_id")
	private Gstr9_DRNT drNT;

	@JsonProperty("exmt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exmt_id")
	private Gstr9_Exmt exmt;

	@JsonProperty("nil")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nil_id")
	private Gstr9_Nil nil;

	@JsonProperty("non_gst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "non_gst_id")
	private Gstr9_NonGst nonGst;

	@JsonProperty("rchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rchrg_id")
	private Gstr9_Rchrg rchrg;

	@JsonProperty("sez")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sez_id")
	private Gstr9_Sez sez;

	@JsonProperty("sub_totalAF")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalAF_id")
	private Gstr9_SubTotalAF subTotalAF;

	@JsonProperty("sub_totalHK")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalHK_id")
	private Gstr9_SubTotalHK subTotalHK;

	@JsonProperty("total_tover")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_tover_id")
	private Gstr9_TotalTover totalTover;

	@JsonProperty("tover_tax_np")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tover_tax_np_id")
	private Gstr9_ToverTaxNP toverTaxNP;

	@JsonProperty("zero_rtd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "zero_rtd_id")
	private Gstr9_ZeroRtd zeroRtd;

	@JsonProperty("ecom_14")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_14_id")
	private Gstr9_Ecom_14 ecom14;

}