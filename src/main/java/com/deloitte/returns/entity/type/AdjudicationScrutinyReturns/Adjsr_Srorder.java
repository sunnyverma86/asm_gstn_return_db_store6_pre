package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

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
@Table(name = "srorder", schema = "adjudication_scrutiny_returns")
public class Adjsr_Srorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("srorderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "srorderdata_id")
	private Adjsr_Srorderdata srorderdata;

}
