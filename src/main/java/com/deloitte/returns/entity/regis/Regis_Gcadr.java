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
@Table(name = "gcadr", schema = "regis")
public class Regis_Gcadr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dst")
	private String dst;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("locality")
	private String locality;

	@JsonProperty("geocodeLevel")
	private String geocodeLevel;

	@JsonProperty("areaNam")
	private String areaNam;

	@JsonProperty("pinCd")
	private String pinCD;

	@JsonProperty("floorNum")
	private String floorNum;

	@JsonProperty("bldgNam")
	private String bldgNam;

	@JsonProperty("doorNum")
	private String doorNum;

	@JsonProperty("stateCd")
	private String stateCD;

	@JsonProperty("streetNam")
	private String streetNam;

	@JsonProperty("landmark")
	private String landmark;

	@JsonProperty("longitude")
	private String longitude;

}
