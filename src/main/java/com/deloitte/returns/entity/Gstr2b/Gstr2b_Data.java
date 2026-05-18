package com.deloitte.returns.entity.Gstr2b;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "data", schema = "gstr2b")
@Entity
public class Gstr2b_Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fc")
	private Double fc;

	@JsonProperty("gendt")
	private String gendt;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("rtnprd")
	private String rtnprd;

	@JsonProperty("version")
	private String version;

	@JsonProperty("cpsumm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpsumm_id")
	private Gstr2b_Cpsumm cpsumm;

	@JsonProperty("docdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private Gstr2b_Docdata docdata;

	@JsonProperty("itcsumm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itcsumm_id")
	private Gstr2b_Itcsumm itcsumm;

	@JsonProperty("docRejdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docRejdata_id")
	private Gstr2b_DocRejdata docRejdata;

	@JsonProperty("cpSummRej")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpSummRej_id")
	private Gstr2b_CpSummRej cpSummRej;

}