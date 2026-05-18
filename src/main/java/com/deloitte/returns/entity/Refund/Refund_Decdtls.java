package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "decdtls", schema = "refund")
public class Refund_Decdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("sign_typ")
	private String signTyp;

	@JsonProperty("undertaking_1")
	private String undertaking1;

	@JsonProperty("self_decl")
	private String selfDecl;

	@JsonProperty("verification")
	private String verification;

	@JsonProperty("dec_1")
	private String dec_1;

	@JsonProperty("dec_2")
	private String dec_2;

	@JsonProperty("dec_3")
	private String dec_3;

	@JsonProperty("dec_4")
	private String dec_4;

	@JsonProperty("dec_5")
	private String dec_5;
	
	@JsonProperty("dec_6")
	private String dec_6;

	@JsonProperty("undertaking_2")
	private String undertaking_2;

}