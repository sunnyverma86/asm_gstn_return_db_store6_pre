package com.deloitte.returns.entity.type.SearchTaxPayer;

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
@Table(name = "search_tax_payer", schema = "search_tax_payer")
public class SearchTaxPayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("stjCd")
	private String stjCD;

	@JsonProperty("lgnm")
	private String lgnm;

	@JsonProperty("stj")
	private String stj;

	@JsonProperty("dty")
	private String dty;

	@JsonProperty("cxdt")
	private String cxdt;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("einvoiceStatus")
	private String einvoiceStatus;

	@JsonProperty("nba")
	private String nba;

	@JsonProperty("lstupdt")
	private String lstupdt;

	@JsonProperty("rgdt")
	private String rgdt;

	@JsonProperty("ctb")
	private String ctb;

	@JsonProperty("sts")
	private String sts;

	@JsonProperty("ctjCd")
	private String ctjCD;

	@JsonProperty("ctj")
	private String ctj;

	@JsonProperty("tradeNam")
	private String tradeNam;

	@JsonProperty("pradr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pradr_id")
	private PublicSTP_Pradr pradr;

	@JsonProperty("adadr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "search_tax_payer_id")
	private List<PublicSTP_Adadr> adadr;
	
	


}