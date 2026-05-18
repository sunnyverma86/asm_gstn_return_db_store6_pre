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
@Table(name = "sdtls", schema = "adjudication_provisional_assessment")
public class Adjpa_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("paadifr2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paadifr2_id")
	private Adjpa_Paadifr2 paadifr2;

	@JsonProperty("proorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "proorder_id")
	private Adjpa_Proorder  proorder;

	@JsonProperty("drprcnf")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcnf_id")
	private Adjpa_Drprcnf drprcnf;
	
	@JsonProperty("relfunsec")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "relfunsec_id")
	 private Adjpa_Relfunsec relfunsec;

	    

}