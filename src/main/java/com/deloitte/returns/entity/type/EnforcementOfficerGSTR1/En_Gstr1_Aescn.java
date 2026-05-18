package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "aescn", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Aescn {

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

	@JsonProperty("name")
	private String name;

	@JsonProperty("orgorddt")
	private String orgorddt;

	@JsonProperty("orgorderid")
	private String orgorderid;

	@JsonProperty("origDemandId")
	private String origDemandID;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("type")
	private String type;

	@JsonProperty("venu")
	private String venu;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private En_Gstr1_Tpovl tpovl;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<En_Gstr1_Annxdocs> annxdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<En_Gstr1_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<En_Gstr1_Suppdocs> suppdocs;
}