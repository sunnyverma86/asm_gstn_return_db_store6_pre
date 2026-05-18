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
@Table(name = "table4", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table4 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("b2b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private En_Gstr9_B2B b2B;

	@JsonProperty("b2c")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2c_id")
	private En_Gstr9_B2C b2C;
	
	@JsonProperty("exp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exp_id")
	private En_Gstr9_Exp exp;
	
	@JsonProperty("ecom")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private En_Gstr9_Ecom ecom;

	@JsonProperty("sez")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sez_id")
	private En_Gstr9_Sez sez;
	
	@JsonProperty("deemed")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deemed_id")
	private En_Gstr9_Deemed deemed;
	
	@JsonProperty("at")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "at_id")
	private En_Gstr9_At at;
	
	@JsonProperty("rchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rchrg_id")
	private En_Gstr9_Rchrg rchrg;
	
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
	
	@JsonProperty("sub_totalAG")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalAG_id")
	private En_Gstr9_SubTotalAG subTotalAG;

	@JsonProperty("sub_totalAG1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalAG1_id")
	private En_Gstr9_SubTotalAG1 subTotalAG1;

	
	@JsonProperty("sub_totalIL")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalIL_id")
	private En_Gstr9_SubTotalIL subTotalIL;

	@JsonProperty("sup_adv")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sup_adv_id")
	private En_Gstr9_SupAdv supAdv;












}
