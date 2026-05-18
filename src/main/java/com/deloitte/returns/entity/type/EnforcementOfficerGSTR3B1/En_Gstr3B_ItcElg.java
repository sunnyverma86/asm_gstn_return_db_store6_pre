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
@Table(name = "itc_elg", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_ItcElg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("itc_net")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_net_id")
	private En_Gstr3B_ItcNet itcNet;

	@JsonProperty("itc_avl")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<En_Gstr3B_ItcAvl> itcAvl;

	@JsonProperty("itc_rev")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<En_Gstr3B_ItcRev> itcRev;

	@JsonProperty("itc_inelg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<En_Gstr3B_ItcInelg> itcInelg;
	
}
