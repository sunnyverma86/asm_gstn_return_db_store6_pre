package com.deloitte.returns.entity.type.AdjudicationAppeal;

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
@Table(name = "decdtls", schema = "adjudication_appeal")
public class Appel_Decdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("asdes")
	private String asdes;

	@JsonProperty("asnm")
	private String asnm;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("pan")
	private String pan;

	@JsonProperty("pl")
	private String pl;

	@JsonProperty("signty")
	private String signty;

}