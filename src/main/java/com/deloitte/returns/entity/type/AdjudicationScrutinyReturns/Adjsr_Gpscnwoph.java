package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

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
@Table(name = "gpscnwoph", schema = "adjudication_scrutiny_returns")
public class Adjsr_Gpscnwoph {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("venu")
	private String venu;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("type")
	private String type;

	@JsonProperty("facts")
	private String facts;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("grounds")
	private String grounds;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjsr_Tpovl tpovl;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private List<Adjsr_Maindocs> maindocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private List<Adjsr_Dmddtls> dmddtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private List<Adjsr_Suppdocs> suppdocs;
	
	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private List<Adjsr_Annxdocs> annxdocs;

}
