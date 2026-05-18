package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "itm_det", schema = "enforcement_officer_gstr1")
public class En_Gstr1_ItmDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("csamt")//
	private double csamt;

	@JsonProperty("samt")//
	private double samt;

	@JsonProperty("rt")//
	private double rt;

	@JsonProperty("txval")//
	private double txval;

	@JsonProperty("camt")//
	private double camt;

	@JsonProperty("iamt")//
	private double iamt;

}