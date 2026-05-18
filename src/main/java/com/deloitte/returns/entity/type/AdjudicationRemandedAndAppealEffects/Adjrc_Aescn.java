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
@Table(name = "aescn", schema = "adjudication_remanded_and_appeal_effects")
public class Adjrc_Aescn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("aporderid")
	private String aporderid;

	@JsonProperty("aporddt")
	private String aporddt;

	@JsonProperty("orgorderid")
	private String orgorderid;

	@JsonProperty("orgorddt")
	private String orgorddt;

	@JsonProperty("name")
	private String name;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("origDemandId")
	private String origDemandID;

	@JsonProperty("type")
	private String type;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("venu")
	private String venu;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjrc_Tpovl tpovl;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aescn_id")
	private List<Adjrc_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aescn_id")
	private List<Adjrc_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aescn_id")
	private List<Adjrc_Annxdocs> annxdocs;
}
