package com.deloitte.returns.entity.type.AdvanceRulingReference1;
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
@Table(name = "arrfapplndata", schema = "advance_ruling_reference")

public class Ararf_Arrfapplndata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("lgnm")
	private String lgnm;

	@JsonProperty("typeOfUserFlag")
	private String typeOfUserFlag;

	@JsonProperty("emailid")
	private String emailid;
	
	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("mobnum")
	private String mobnum;

	@JsonProperty("tdnm")
	private String tdnm;

	@JsonProperty("appfiledfor")
	private String appfiledfor;

	@JsonProperty("arad")
	private String arad;

	@JsonProperty("authname")
	private String authname;

	@JsonProperty("desig")
	private String desig;

	@JsonProperty("sdwnm")
	private String sdwnm;
	
	@JsonProperty("verify1")
	private Boolean verify1;

	@JsonProperty("verify2")
	private Boolean verify2;

	@JsonProperty("verify4")
	private Boolean verify4;

	@JsonProperty("corrad")
	private Boolean corrad;
	
	@JsonProperty("rsad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rsad_id")
	private Ararf_Rsad rsad;

	@JsonProperty("jdtlsst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jdtlsst_id")
	private Ararf_Jdtlsst jdtlsst;

	@JsonProperty("crad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crad_id")
	private Ararf_Crad crad;
	
	@JsonProperty("verdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_Verdtls> verdtls;

	@JsonProperty("mbrList")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_MbrList> mbrList;

	@JsonProperty("ntbz")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_Ntbz> ntbz;

	@JsonProperty("iowarr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_Iowarr> iowarr;

	@JsonProperty("suppdocarr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_Suppdocarr> suppdocarr;

	@JsonProperty("ardcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfapplndata_id")
	private List<Ararf_Ardcupdtls> ardcupdtls;






}
