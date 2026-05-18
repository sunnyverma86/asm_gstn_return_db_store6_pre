package com.deloitte.returns.entity.type.AdjudicationGeneralPenality;

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
@Table(name = "tpovl", schema = "adjudication_general_penality")
public class Adjgp_Tpovl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fromm")
	private String fromm;

	@JsonProperty("fromy")
	private String fromy;

	@JsonProperty("tom")
	private String tom;

	@JsonProperty("toy")
	private String toy;

}