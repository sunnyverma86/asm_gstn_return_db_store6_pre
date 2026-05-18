package com.deloitte.returns.entity.regis;

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
@Table(name = "stsidtls", schema = "regis")
public class Regis_Stsidtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ptrcnum")
	private String ptrcnum;

	@JsonProperty("stexlcnum")
	private String stexlcnum;

	@JsonProperty("ptecnum")
	private String ptecnum;

	@JsonProperty("exlcnm")
	private String exlcnm;

}
