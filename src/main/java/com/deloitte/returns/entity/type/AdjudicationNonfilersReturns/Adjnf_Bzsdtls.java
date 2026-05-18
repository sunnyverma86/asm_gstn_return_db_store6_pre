package com.deloitte.returns.entity.type.AdjudicationNonfilersReturns;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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
@Table(name = "bzsdtls", schema = "adjudication_nonfilers_returns")
public class Adjnf_Bzsdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("saccd")
	@Column(length = 1000)
	private String saccd;

	@JsonProperty("sdes")
	@Column(length = 1000)
	private String sdes;

}