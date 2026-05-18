package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

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
@Table(name = "pymtdtls", schema = "adjudication_scrutiny_returns")
public class Adjsr_Pymtdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dact")
	private String dact;

	@JsonProperty("dtax")
	private String dtax;

	@JsonProperty("dist")
	private String dist;

	@JsonProperty("dtot")
	private String dtot;

	@JsonProperty("dothers")
	private String dothers;

	@JsonProperty("dpnlty")
	private String dpnlty;

	@JsonProperty("tp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tp_id")
	private Adjsr_Tp tp;

}
