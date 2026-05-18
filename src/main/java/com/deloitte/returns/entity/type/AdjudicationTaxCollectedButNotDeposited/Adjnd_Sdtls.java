package com.deloitte.returns.entity.type.AdjudicationTaxCollectedButNotDeposited;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sdtls", schema = "adjudication_tax_collected_but_not_deposited")
public class Adjnd_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("drprcdt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcdt_id")
	private Adjnd_Drprcdt drprcdt;

	@JsonProperty("drprcgp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcgp_id")
	private Adjnd_Drprcgp drprcgp;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjnd_Todtls todtls;

}