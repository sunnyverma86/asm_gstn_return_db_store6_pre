package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;

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
@Table(name = "aedrcorder", schema = "adjudication_unregistered_persons")
public class Adjur_Aedrcorder {

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

	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("orgorddt")
	private String orgorddt;

	@JsonProperty("orgorderid")
	private String orgorderid;

	@JsonProperty("origDemandId")
	private String origDemandID;

	@JsonProperty("othrsn")
	private String othrsn;

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
	private Adjur_Tpovl tpovl;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjur_Gdssvcdtls gdssvcdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private List<Adjur_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private List<Adjur_Annxdocs> annxdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private List<Adjur_Dmddtls> dmddtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private List<Adjur_Suppdocs> suppdocs;

}