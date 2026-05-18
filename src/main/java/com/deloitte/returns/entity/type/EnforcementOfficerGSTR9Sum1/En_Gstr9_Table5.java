package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
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
@Table(name = "table5", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table5 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("zero_rtd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "zero_rtd_id")
	private En_Gstr9_ZeroRtd zeroRtd;
	
	@JsonProperty("sez")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sez_id")
	private En_Gstr9_Sez sez;
	
	@JsonProperty("rchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rchrg_id")
	private En_Gstr9_Rchrg rchrg;
	
	@JsonProperty("exmt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exmt_id")
	private En_Gstr9_Exmt exmt;
	
	@JsonProperty("nil")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nil_id")
	private En_Gstr9_Nil nil;
	
	@JsonProperty("ecom_14")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_14_id")
	private En_Gstr9_Ecom14 ecom14;

	
	@JsonProperty("non_gst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "non_gst_id")
	private En_Gstr9_NonGst nonGst;
	
	@JsonProperty("cr_nt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_nt_id")
	private En_Gstr9_CRNT crNT;
	
	@JsonProperty("dr_nt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dr_nt_id")
	private En_Gstr9_DRNT drNT;
	
	@JsonProperty("amd_neg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amd_neg_id")
	private En_Gstr9_AMDNeg amdNeg;

	@JsonProperty("amd_pos")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "amd_pos_id")
	private En_Gstr9_AMDPos amdPos;
	
	@JsonProperty("sub_totalAF")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalAF_id")
	private En_Gstr9_SubTotalAF subTotalAF;

	@JsonProperty("sub_totalHK")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalHK_id")
	private En_Gstr9_SubTotalHK subTotalHK;
	
	@JsonProperty("tover_tax_np")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tover_tax_np_id")
	private En_Gstr9_ToverTaxNP toverTaxNP;
	
	@JsonProperty("total_tover")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_tover_id")
	private En_Gstr9_TotalTover totalTover;




}
