package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doclist", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Doclist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aspd")
	private String aspd;

	@JsonProperty("atyp")
	private String atyp;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("docdt")
	private String docdt;

	@JsonProperty("docnum")
	private String docnum;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("isd_docty")
	private String isdDocty;

	@JsonProperty("itc_elg")
	private String itcElg;

	@JsonProperty("odocdt")
	private String odocdt;

	@JsonProperty("odocnum")
	private String odocnum;



}