package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table6", schema = "gstr9")
@Entity
public class Gstr9_Table6 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("difference")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "difference_id")
	private Gstr9_Difference difference;

	@JsonProperty("ios")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ios_id")
	private Gstr9_Ios ios;

	@JsonProperty("isd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isd_id")
	private Gstr9_Isd isd;

	@JsonProperty("itc_3b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_3b_id")
	private Gstr9_Itc3B itc3B;

	@JsonProperty("itc_clmd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_clmd_id")
	private Gstr9_ItcClmd itcClmd;

	@JsonProperty("other")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "other_id")
	private Gstr9_Other other;

	@JsonProperty("sub_totalBH")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalBH_id")
	private Gstr9_SubTotalBH subTotalBH;

	@JsonProperty("sub_totalKM")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalKM_id")
	private Gstr9_SubTotalKM subTotalKM;

	@JsonProperty("total_itc_availed")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_itc_availed_id")
	private Gstr9_TotalItcAvailed totalItcAvailed;

	@JsonProperty("tran1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tran1_id")
	private Gstr9_Tran1 tran1;

	@JsonProperty("tran2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tran2_id")
	private Gstr9_Tran2 tran2;
	
	@JsonProperty("itc_otr")   //new add
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_otr_id")
	private Gstr9_Itcotr itc_otr;
	
	@JsonProperty("itc_net")   //new add
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_net_id")
	private Gstr9_Itcnet itc_net;

	@JsonProperty("supp_non_rchrg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<Gstr9_SuppNonRchrg> suppNonRchrg;

	@JsonProperty("supp_rchrg_reg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<Gstr9_SuppRchrgReg> suppRchrgReg;

	@JsonProperty("supp_rchrg_unreg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<Gstr9_SuppRchrgUnreg> suppRchrgUnreg;

	@JsonProperty("iog")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<Gstr9_Iog> iog;



}