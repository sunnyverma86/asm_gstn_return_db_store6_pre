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
@Table(name = "gporder", schema = "adjudication_general_penality")
public class Adjgp_Gporder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;
	
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
	
	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("gpdrpprddata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpdrpprddata_id")
	private Adjgp_Gpdrpprddata gpdrpprddata;

	@JsonProperty("gpscndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscndata_id")
	private Adjgp_Gpscndata gpscndata;
	
	@JsonProperty("gpdorderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpdorderdata_id")
	private Adjgp_Gpdorderdata gpdorderdata;
	
	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjgp_Gdssvcdtls gdssvcdtls;
	
	
	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjgp_Tpovl tpovl;
	
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gporder_id")
	private List<Adjgp_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gporder_id")
	private List<Adjgp_Suppdocs> suppdocs;
	
	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gporder_id")
	private List<Adjgp_Dmddtls> dmddtls;
	
	
	
	
	
	

}
