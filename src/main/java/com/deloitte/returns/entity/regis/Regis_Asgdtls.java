package com.deloitte.returns.entity.regis;

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
@Table(name = "asgdtls", schema = "regis")
public class Regis_Asgdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("mn")
	private String mn;

	@JsonProperty("eid")
	private String eid;

	@JsonProperty("ln")
	private String ln;

	@JsonProperty("dg")
	private String dg;

	@JsonProperty("mbno")
	private String mbno;

	@JsonProperty("fhfn")
	private String fhfn;

	@JsonProperty("ispas")
	private String ispas;

	@JsonProperty("em")
	private String em;

	@JsonProperty("fn")
	private String fn;

	@JsonProperty("fhln")
	private String fhln;

	@JsonProperty("fhmn")
	private String fhmn;

	@JsonProperty("iscitind")
	private String iscitind;

	@JsonProperty("ppno")
	private String ppno;

	@JsonProperty("dob")
	private String dob;

	@JsonProperty("gd")
	private String gd;

	@JsonProperty("adhrEnrolTmstmp")
	private String adhrEnrolTmstmp;

	@JsonProperty("adhrEnrolId")
	private String adhrEnrolId;

	@JsonProperty("rgtodt")
	private String rgtodt;

	@JsonProperty("tlphno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tlphno_id")
	private Regis_Tlphno tlphno;

	@JsonProperty("din")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "din_id")
	private Regis_Din din;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asgdtls_id")
	private List<Regis_Dcupdtls> dcupdtls;

	@JsonProperty("uid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid_id")
	private Regis_Uid uid;

	@JsonProperty("rsad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rsad_id")
	private Regis_Rsad rsad;

	@JsonProperty("pan")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pan_id")
	private Regis_Pan pan;

	@JsonProperty("adhdcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asgdtls_id")
	private List<Regis_Adhdcupdtls> adhdcupdtls;

	@JsonProperty("adhrAuthTyp")
	private String adhrAuthTyp;

}
