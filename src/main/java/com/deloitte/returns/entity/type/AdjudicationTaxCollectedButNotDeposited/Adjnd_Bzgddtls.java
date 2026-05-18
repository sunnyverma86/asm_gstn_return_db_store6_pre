package com.deloitte.returns.entity.type.AdjudicationTaxCollectedButNotDeposited;

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
@Table(name = "bzgddtls", schema = "adjudication_tax_collected_but_not_deposited")
public class Adjnd_Bzgddtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gdes")
	private String gdes;

	@JsonProperty("hsncd")
	private String hsncd;

}