package com.deloitte.returns.entity.Gstr6;

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
@Table(name = "isdItcCross", schema = "gstr6")
@Entity
public class Gstr6_IsdItcCross {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private double camt;

	@JsonProperty("camtc")
	private double camtc;

	@JsonProperty("camti")
	private double camti;

	@JsonProperty("cess")
	private double cess;

	@JsonProperty("csamt")
	private double csamt;

	@JsonProperty("iamt")
	private double iamt;

	@JsonProperty("iamtc")
	private double iamtc;

	@JsonProperty("iamti")
	private double iamti;

	@JsonProperty("iamts")
	private double iamts;

	@JsonProperty("samt")
	private double samt;

	@JsonProperty("samti")
	private double samti;

	@JsonProperty("samts")
	private double samts;

}