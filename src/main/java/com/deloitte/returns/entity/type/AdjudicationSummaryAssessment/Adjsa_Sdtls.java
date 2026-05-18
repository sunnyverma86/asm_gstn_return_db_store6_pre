package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sdtls", schema = "adjudication_summary_assessment")
public class Adjsa_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("srcoff")
	private String srcoff;

	@JsonProperty("receivingmodule")
	private String receivingmodule;

	@JsonProperty("section")
	private String section;

	@JsonProperty("comments")
	private String comments;

	@JsonProperty("officer")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "officer_id")
	private Adjsa_Officer officer;

	@JsonProperty("ordrsa")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordrsa_id")
	private Adjsa_Ordrsa ordrsa;

	@JsonProperty("docModel")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<Adjsa_DocModel> docModel;
	
	

}