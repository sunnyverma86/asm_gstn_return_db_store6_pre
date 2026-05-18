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
@Table(name = "dtorder", schema = "adjudication_general_penality")
public class Adjgp_Dtorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("transTypeCd")
	private Long transTypeCD;

	@JsonProperty("type")
	private String type;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("sec")
	private String sec;
	
	@JsonProperty("othrsn")
	private String othrsn;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "_id")
	private Adjgp_Tpovl tpovl;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjgp_Gdssvcdtls gdssvcdtls;
	
	@JsonProperty("sdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private Adjgp_Sdtls sdtls;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjgp_Todtls todtls;

	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorder_id")
	private List<Adjgp_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorder_id")
	private List<Adjgp_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorder_id")
	private List<Adjgp_Dmddtls> dmddtls;
	
	

}