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
@Table(name = "urscnwph", schema = "adjudication_unregistered_persons")

public class Adjur_Urscnwph {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("lbltoreg")
	private String lbltoreg;

	@JsonProperty("candt")
	private String candt;

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

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("pht")
	private String pht;
	
	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("grounds")
	private String grounds;
	
	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjur_Tpovl tpovl;
	
	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urscnwph_id")
	private List<Adjur_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urscnwph_id")
	private List<Adjur_Dmddtls> dmddtls;
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urscnwph_id")
	private List<Adjur_Maindocs> maindocs;





}
