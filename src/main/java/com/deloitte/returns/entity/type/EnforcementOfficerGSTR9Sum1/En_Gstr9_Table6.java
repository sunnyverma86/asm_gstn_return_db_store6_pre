package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
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
@Table(name = "table6", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table6 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("itc_3b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_3b_id")
	private En_Gstr9_Itc3B itc3B;
	
	@JsonProperty("supp_non_rchrg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<En_Gstr9_SuppNonRchrg> suppNonRchrg;

	@JsonProperty("supp_rchrg_reg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<En_Gstr9_SuppRchrgReg> suppRchrgReg;

	@JsonProperty("supp_rchrg_unreg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<En_Gstr9_SuppRchrgUnreg> suppRchrgUnreg;
	
	@JsonProperty("iog")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<En_Gstr9_Iog> iog;
	
	@JsonProperty("ios")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ios_id")
	private En_Gstr9_Ios ios;

	@JsonProperty("isd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isd_id")
	private En_Gstr9_Isd isd;
	
	@JsonProperty("itc_clmd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_clmd_id")
	private En_Gstr9_ItcClmd itcClmd;
	
	@JsonProperty("tran1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tran1_id")
	private En_Gstr9_Tran1 tran1;

	@JsonProperty("tran2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tran2_id")
	private En_Gstr9_Tran2 tran2;
	
	@JsonProperty("other")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "other_id")
	private En_Gstr9_Other other;
	
	@JsonProperty("sub_totalBH")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalBH_id")
	private En_Gstr9_SubTotalBH subTotalBH;
	
	@JsonProperty("difference")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "difference_id")
	private En_Gstr9_Difference difference;
	

	@JsonProperty("sub_totalKM")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_totalKM_id")
	private En_Gstr9_SubTotalKM subTotalKM;

	@JsonProperty("total_itc_availed")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_itc_availed_id")
	private En_Gstr9_TotalItcAvailed totalItcAvailed;

	@JsonProperty("itc_net")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_net_id")
	private En_Gstr9_Itcnet itcnet;

	@JsonProperty("itc_otr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_otr_id")
	private En_Gstr9_Itcotr itcotr;


	
}
