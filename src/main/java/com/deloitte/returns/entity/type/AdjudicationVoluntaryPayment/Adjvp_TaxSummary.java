package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

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
@Table(name = "taxsummary", schema = "adjudication_voluntary_payment")


public class Adjvp_TaxSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("intr")
	private Double intr;
	
	@JsonProperty("oth")
	private Double oth;
	
	@JsonProperty("fee")
	private Double fee;
	
	@JsonProperty("pen")
	private Double pen;
	
	@JsonProperty("tot")
	private Double tot;
	
	@JsonProperty("tax")
	private Double tax;
	

}
