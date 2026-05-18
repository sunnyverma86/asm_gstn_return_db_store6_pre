package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

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
@Table(name = "bankdetails", schema = "adjudication_provisional_assessment")
public class Adjpa_Bankdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("mode")
	private String mode;

	@JsonProperty("bankguarefno")
	private String bankguarefno;

	@JsonProperty("nameofbank")
	private String nameofbank;

	@JsonProperty("date")
	private String date;

	@JsonProperty("amount")
	private String amount;

}
