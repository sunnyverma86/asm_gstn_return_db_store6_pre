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
@Table(name = "ovtp", schema = "adjudication_voluntary_payment")
public class Adjvp_Ovtp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("frommonth")
	private String frommonth;

	@JsonProperty("fromyear")
	private String fromyear;

	@JsonProperty("tomonth")
	private String tomonth;

	@JsonProperty("toyear")
	private String toyear;

}