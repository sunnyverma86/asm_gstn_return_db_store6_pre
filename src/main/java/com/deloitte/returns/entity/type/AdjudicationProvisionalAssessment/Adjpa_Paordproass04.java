package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

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
@Table(name = "paordproass04", schema = "adjudication_provisional_assessment")
public class Adjpa_Paordproass04 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("paordproass04data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paordproass04data_id")
	private Adjpa_Paordproass04Data paordproass04Data;

}
