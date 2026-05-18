package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "remandedordr", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Remandedordr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aporddt")
	private String aporddt;

	@JsonProperty("aporderid")
	private String aporderid;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("orgorddt")
	private String orgorddt;

	@JsonProperty("orgorderid")
	private String orgorderid;

	@JsonProperty("origDemandId")
	private String origDemandI;

	@JsonProperty("remandedby")
	private String remandedby;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("transTypeCd")
	private String transTypeCD;

	@JsonProperty("type")
	private String type;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private En_Gstr1_Tpovl tpovl;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<En_Gstr1_Annxdocs> annxdocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<En_Gstr1_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<En_Gstr1_Dmddtls> dmddtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<En_Gstr1_Maindocs> maindocs;

}