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
@Table(name = "bzdtlsbz", schema = "regis")
public class Regis_Bzdtlsbz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("librgdt")
	private String librgdt;

	@JsonProperty("authstatus")
	private String authstatus;

	@JsonProperty("dst")
	private String dst;

	@JsonProperty("isopcmp")
	private String isopcmp;

	@JsonProperty("lgnmbzpan")
	private String lgnmbzpan;

	@JsonProperty("antoamt")
	private String antoamt;

	@JsonProperty("cobz")
	private String cobz;

	@JsonProperty("cmbzdt")
	private String cmbzdt;

	@JsonProperty("regtypecd")
	private String regtypecd;

	@JsonProperty("apprvdt")
	private String apprvdt;

	@JsonProperty("cobz_oth")
	private String cobzOth;

	@JsonProperty("rgfmdt")
	private String rgfmdt;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("libdt")
	private String libdt;

	@JsonProperty("estspamt")
	private String estspamt;

	@JsonProperty("rslibrg")
	private String rslibrg;

	@JsonProperty("iscasdl")
	private String iscasdl;

	@JsonProperty("adhrEnrolTmstmp")
	private String adhrEnrolTmstmp;

	@JsonProperty("rgtodt")
	private String rgtodt;
	
	@JsonProperty("regCategory")
	private List<String> regCategory;
	

	@JsonProperty("addltrdnms")
	private List<String> addltrdnms;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bzdtlsbz_id")
	private List<Regis_Dcupdtls> dcupdtls;

	
	
	@JsonProperty("pan")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pan_id")
	private Regis_Pan pan;

}
