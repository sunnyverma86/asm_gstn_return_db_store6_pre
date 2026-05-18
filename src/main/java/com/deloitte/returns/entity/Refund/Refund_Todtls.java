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
@Table(name = "todtls", schema = "refund")
public class Refund_Todtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dg")
	private String dg;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("nm")
	private String nm;

	@JsonProperty("pl")
	private String pl;

	@JsonProperty("pn")
	private String pn;

	@JsonProperty("signty")
	private String signty;

	@JsonProperty("toid")
	private String toid;

}