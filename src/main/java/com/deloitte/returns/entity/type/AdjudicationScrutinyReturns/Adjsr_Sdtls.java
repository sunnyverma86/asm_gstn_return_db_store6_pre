package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "sdtls", schema = "adjudication_scrutiny_returns")
public class Adjsr_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("receivingmodule")
	private String receivingmodule;

	@JsonProperty("comments")
	@Column(length = 2000)
	private String comments;

	@JsonProperty("srcoff")
	private String srcoff;

	@JsonProperty("section")
	private String section;

	@JsonProperty("type")
	private String type;

	@JsonProperty("srscn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "srscn_id")
	private Adjsr_Srscn srscn;

	@JsonProperty("drprcsr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcsr_id")
	private Adjsr_Drprcsr drprcsr;

	@JsonProperty("officer")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "officer_id")
	private Adjsr_Officer officer;

	@JsonProperty("docModel")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<Adjsr_DocModel> docModel;

}