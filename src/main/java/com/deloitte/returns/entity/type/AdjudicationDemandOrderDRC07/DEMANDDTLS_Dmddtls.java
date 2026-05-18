package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

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
@Table(name = "dmddtls", schema = "demand_order_drc07")
public class DEMANDDTLS_Dmddtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dact")
	private String dact;

	@JsonProperty("dfees")
	private String dfees;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("trnovr")
	private String trnovr;

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

	@JsonProperty("tr")
	private String tr;

}