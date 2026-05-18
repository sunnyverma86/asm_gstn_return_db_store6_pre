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
@Table(name = "dmddtls", schema = "adjudication_determination_tax")
public class Adjadt_Dmddtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dact")
	private String dact;
	
	@JsonProperty("dfees")
	private String dfees;

	@JsonProperty("dist")
	private String dist;

	@JsonProperty("dothers")
	private String dothers;

	@JsonProperty("dpnlty")
	private String dpnlty;

	@JsonProperty("dtax")
	private String dtax;

	@JsonProperty("dtot")
	private String dtot;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("trnovr")
	private String trnovr;
	
	@JsonProperty("tr")
	private String tr;

	@JsonProperty("tp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tp_id")
	private Adjadt_Tp tp;

}