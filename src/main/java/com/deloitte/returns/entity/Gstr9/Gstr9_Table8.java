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
@Table(name = "table8", schema = "gstr9")
@Entity
public class Gstr9_Table8 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("differenceABC")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "differenceABC_id")
	private Gstr9_DifferenceABC differenceABC;

	@JsonProperty("differenceGH")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "differenceGH_id")
	private Gstr9_DifferenceGH differenceGH;

	@JsonProperty("iog_itc_availd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iog_itc_availd_id")
	private Gstr9_IogItcAvaild iogItcAvaild;

	@JsonProperty("iog_itc_ntavaild")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iog_itc_ntavaild_id")
	private Gstr9_IogItcNtavaild iogItcNtavaild;

	@JsonProperty("iog_taxpaid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iog_taxpaid_id")
	private Gstr9_IogTaxpaid iogTaxpaid;

	@JsonProperty("itc_2a")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_2a_id")
	private Gstr9_Itc2A itc2A;

	@JsonProperty("itc_inwd_supp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_inwd_supp_id")
	private Gstr9_ItcInwdSupp itcInwdSupp;

	@JsonProperty("itc_nt_availd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_nt_availd_id")
	private Gstr9_ItcNTAvaild itcNTAvaild;

	@JsonProperty("itc_nt_eleg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_nt_eleg_id")
	private Gstr9_ItcNTEleg itcNTEleg;

	@JsonProperty("itc_tot")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_tot_id")
	private Gstr9_ItcTot itcTot;

	@JsonProperty("tot_itc_lapsed")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_itc_lapsed_id")
	private Gstr9_TotItcLapsed totItcLapsed;

	@JsonProperty("itc_2b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_2b_id")
	private Gstr9_Itc2B itc2B;


	@JsonProperty("iog_nxt_yr")  //new add
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iog_nxt_yr_id")
	private Gstr9_Iognxtyr iog_nxt_yr;

}