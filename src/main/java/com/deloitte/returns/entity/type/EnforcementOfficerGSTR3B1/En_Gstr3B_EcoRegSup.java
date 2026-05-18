package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

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
@Table(name = "eco_reg_sup", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_EcoRegSup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("csamt")
	private double csamt;

	@JsonProperty("samt")
	private double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("camt")
	private double camt;

	@JsonProperty("iamt")
	private double iamt;

	@JsonProperty("ty")
	private String ty;

}