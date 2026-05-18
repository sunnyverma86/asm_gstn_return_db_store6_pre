package com.deloitte.returns.entity.type.AdjudicationRemandedAndAppealEffects;

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
@Table(name = "remandedordr", schema = "adjudication_remanded_and_appeal_effects")
public class Adjrc_Remandedordr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("aporderid")
	private String aporderid;

	@JsonProperty("remandedby")
	private String remandedby;

	@JsonProperty("transTypeCd")
	private String transTypeCD;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("aporddt")
	private String aporddt;

	@JsonProperty("orgorddt")
	private String orgorddt;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("origDemandId")
	private String origDemandID;

	@JsonProperty("orgorderid")
	private String orgorderid;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("othrsn")
	private String othrsn;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjrc_Tpovl tpovl;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjrc_Gdssvcdtls gdssvcdtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<Adjrc_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<Adjrc_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<Adjrc_Annxdocs> annxdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private List<Adjrc_Dmddtls> dmddtls;

}
