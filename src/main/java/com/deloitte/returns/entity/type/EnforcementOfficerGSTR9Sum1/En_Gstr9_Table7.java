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
@Table(name = "table7", schema = "enforcement_officer_gstr9")

public class En_Gstr9_Table7 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("rule37")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rule37_id")
	private En_Gstr9_Rule37 rule37;

	@JsonProperty("rule39")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rule39_id")
	private En_Gstr9_Rule39 rule39;

	@JsonProperty("rule42")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rule42_id")
	private En_Gstr9_Rule42 rule42;

	@JsonProperty("rule43")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rule43_id")
	private En_Gstr9_Rule43 rule43;

	@JsonProperty("sec17")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sec17_id")
	private En_Gstr9_Sec17 sec17;
	
	@JsonProperty("revsl_tran1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "revsl_tran1_id")
	private En_Gstr9_RevslTran1 revslTran1;

	@JsonProperty("revsl_tran2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "revsl_tran2_id")
	private En_Gstr9_RevslTran2 revslTran2;
	
	@JsonProperty("other")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table7_id")
	private List<En_Gstr9_Other> other;
	
	@JsonProperty("tot_itc_revd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_itc_revd_id")
	private En_Gstr9_TotItcRevd totItcRevd;
	
	@JsonProperty("net_itc_aval")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "net_itc_aval_id")
	private En_Gstr9_NetItcAval netItcAval;



}
