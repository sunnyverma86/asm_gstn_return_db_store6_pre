package com.deloitte.returns.entity.type.AdjudicationGeneralPenality;

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
@Table(name = "dtscn", schema = "adjudication_general_penality")
public class Adjgp_Dtscn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("facts")
	private String facts;

	@JsonProperty("fetr")
	private String fetr;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("grounds")
	private String grounds;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("sec")
	private String sec;
	
	@JsonProperty("reason")
	private String reason;

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("venu")
	private String venu;
	
	@JsonProperty("phdt")
	private String phdt;
	
	@JsonProperty("pht")
	private String pht;
	
	
	@JsonProperty("scnrefno")
	private String scnrefno;
	
	
	@JsonProperty("replyDuedt")
	private String replyDuedt;
	
	@JsonProperty("pymtDuedt")
	private String pymtDuedt;
	

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjgp_Tpovl tpovl;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private List<Adjgp_Dmddtls> dmddtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private List<Adjgp_Maindocs> maindocs;
	
	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private List<Adjgp_Annxdocs> annxdocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private List<Adjgp_Suppdocs> suppdocs;

}