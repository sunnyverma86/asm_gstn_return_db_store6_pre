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
@Table(name = "opdtls", schema = "regis")
public class Regis_Opdtls {

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

	@JsonProperty("isasg")
	private String isasg;

	@JsonProperty("adhrEnrolTmstmp")
	private String adhrEnrolTmstmp;

	@JsonProperty("adhrEnrolId")
	private String adhrEnrolId;
	
	@JsonProperty("rgtodt")
	private String rgtodt;

	@JsonProperty("uid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid_id")
	private Regis_Uid uid;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "opdtls_id")
	public List<Regis_Dcupdtls> dcupdtls;

	@JsonProperty("rsad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rsad_id")
	private Regis_Rsad rsad;

	@JsonProperty("pan")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pan_id")
	private Regis_Pan pan;

	@JsonProperty("din")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "din_id")
	private Regis_Din din;

	@JsonProperty("tlphno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tlphno_id")
	private Regis_Tlphno tlphno;

	@JsonProperty("adhdcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "opdtls_id")
	private List<Regis_Adhdcupdtls> adhdcupdtls;
	
	@JsonProperty("adhrAuthTyp")
	private String adhrAuthTyp;

}
