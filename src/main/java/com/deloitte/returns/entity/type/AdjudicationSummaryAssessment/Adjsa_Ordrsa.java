package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

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
@Table(name = "ordrsa", schema = "adjudication_summary_assessment")
public class Adjsa_Ordrsa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("lctnofvhcl")
	private String lctnofvhcl;

	@JsonProperty("transTypeCd")
	private String transTypeCD;

	@JsonProperty("type")
	private String type;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("godnadrs")
	private String godnadrs;

	@JsonProperty("vhcldtl")
	private String vhcldtl;

	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjsa_Tpovl tpovl;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjsa_Gdssvcdtls gdssvcdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordrsa_id")
	private List<Adjsa_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordrsa_id")
	private List<Adjsa_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordrsa_id")
	private List<Adjsa_Dmddtls> dmddtls;

	@JsonProperty("acjc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordrsa_id")
	private List<Adjsa_Acjc> acjc;

}
