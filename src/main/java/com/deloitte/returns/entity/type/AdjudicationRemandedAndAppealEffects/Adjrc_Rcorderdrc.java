package com.deloitte.returns.entity.type.AdjudicationRemandedAndAppealEffects;

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
@Table(name = "rcorderdrc", schema = "adjudication_remanded_and_appeal_effects")
public class Adjrc_Rcorderdrc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("rcorderdrcdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rcorderdrcdata_id")
	private Adjrc_Rcorderdrcdata rcorderdrcdata;


}
