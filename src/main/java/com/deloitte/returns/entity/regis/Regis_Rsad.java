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
@Table(name = "rsad", schema = "regis")
public class Regis_Rsad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bnm")
	private String bnm;

	@JsonProperty("st")
	private String st;

	@JsonProperty("loc")
	private String loc;

	@JsonProperty("bno")
	private String bno;

	@JsonProperty("dst")
	private String dst;

	@JsonProperty("locsubloc")
	private String locsubloc;

	@JsonProperty("lt")
	private String lt;

	@JsonProperty("pncd")
	private String pncd;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("flno")
	private String flno;

	@JsonProperty("lg")
	private String lg;

	@JsonProperty("nrbyLdMrk")
	private String nrbyLdMrk;

	@JsonProperty("geoCodeLevel")
	private String geoCodeLevel;

	@JsonProperty("cnty")
	private String cnty;

}
