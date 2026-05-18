package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

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
@Table(name = "commdtls", schema = "adjudication_provisional_assessment")
public class Adjpa_Commdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("hsn")
	private String hsn;

	@JsonProperty("commnm")
	@Column(length = 1000)
	private String commnm;

	@JsonProperty("trigst")
	private String trigst;

	@JsonProperty("trcgst")
	private double trcgst;

	@JsonProperty("trsgst")
	private double trsgst;

	@JsonProperty("val")
	private String val;

	@JsonProperty("avgmntr")
	private String avgmntr;

}
