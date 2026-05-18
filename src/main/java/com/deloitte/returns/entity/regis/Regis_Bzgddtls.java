package com.deloitte.returns.entity.regis;

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
@Table(name = "bzgddtls", schema = "regis")
public class Regis_Bzgddtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eid")
	@Column(length = 250)
	private String eid;

	@JsonProperty("gdes")
	@Column(length = 3000)
	private String gdes;

	@JsonProperty("hsncd")
	@Column(length = 500)
	private String hsncd;

}
