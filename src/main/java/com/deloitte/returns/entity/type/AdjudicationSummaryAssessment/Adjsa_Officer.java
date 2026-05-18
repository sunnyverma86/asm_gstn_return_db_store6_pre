package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

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
@Table(name = "officer", schema = "adjudication_summary_assessment")
public class Adjsa_Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("taxOffclID")
	private String taxOffclID;

	@JsonProperty("taxoffclMname")
	private String taxoffclMname;

	@JsonProperty("stateJursdCd")
	private String stateJursdCD;

	@JsonProperty("accessMapID")
	private String accessMapID;

	@JsonProperty("taxoffclLname")
	private String taxoffclLname;

	@JsonProperty("accessGrpDesc")
	private String accessGrpDesc;

	@JsonProperty("accessGrpId")
	private String accessGrpID;

	@JsonProperty("taxOffclSalut")
	private String taxOffclSalut;

	@JsonProperty("taxoffclFname")
	private String taxoffclFname;

	@JsonProperty("disgnation")
	private String disgnation;

}
