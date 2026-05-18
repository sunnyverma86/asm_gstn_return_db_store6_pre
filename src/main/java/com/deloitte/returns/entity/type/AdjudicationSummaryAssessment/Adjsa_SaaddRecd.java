package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saaddRecd", schema = "adjudication_summary_assessment")
public class Adjsa_SaaddRecd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("saRecomm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saRecomm_id")
	private Adjsa_SaRecomm saRecomm;

}
