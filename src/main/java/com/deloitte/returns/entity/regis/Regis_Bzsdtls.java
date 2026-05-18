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
@Table(name = "bzsdtls", schema = "regis")
public class Regis_Bzsdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eid")
	private String eid;

	@JsonProperty("saccd")
	private String saccd;

	@JsonProperty("sdes")
	@Column(name = "sdes", length = 7000)
	private String sdes;

}
