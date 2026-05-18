package com.deloitte.returns.entity.type.AdjudicationDeterminationTax;

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
@Table(name = "sdtls", schema = "adjudication_determination_tax")
public class Adjadt_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dtscn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private Adjadt_Dtscn dtscn;

	@JsonProperty("drprcdt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcdt_id")
	private Adjadt_Drprcdt drprcdt;

	@JsonProperty("drprcgp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcgp_id")
	private Adjadt_Drprcgp drprcgp;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjadt_Todtls todtls;

	@JsonProperty("dtorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorder_id")
	private Adjadt_Dtorder dtorder;

	@JsonProperty("gpscnwoph")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private Adjadt_Gpscnwoph gpscnwoph;

}