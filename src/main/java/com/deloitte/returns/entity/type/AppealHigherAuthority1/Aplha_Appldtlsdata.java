package com.deloitte.returns.entity.type.AppealHigherAuthority1;
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
@Table(name = "appldtlsdata", schema = "appeal_higher_authority")

public class Aplha_Appldtlsdata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("legnm")
	private String legnm;

	@JsonProperty("tpaddr")
	private String tpaddr;

	@JsonProperty("trdnm")
	private String trdnm;
	
	@JsonProperty("appealno")
	private String appealno;

	@JsonProperty("filingdt")
	private String filingdt;

	@JsonProperty("filedbefore")
	private String filedbefore;

	@JsonProperty("appellant")
	private String appellant;

	@JsonProperty("respondent")
	private String respondent;

	@JsonProperty("hearingdt")
	private String hearingdt;
	
	@JsonProperty("orddtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private Aplha_Orddtl orddtl;

	@JsonProperty("stayorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stayorder_id")
	private Aplha_Stayorder stayorder;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Aplha_Todtls todtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appldtlsdata_id")
	private List<Aplha_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appldtlsdata_id")
	private List<Aplha_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appldtlsdata_id")
	private List<Aplha_Annxdocs> annxdocs;




}
