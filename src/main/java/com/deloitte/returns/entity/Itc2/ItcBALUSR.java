package com.deloitte.returns.entity.Itc2;

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
@Table(name = "itc_bal_usr", schema = "itc02")
@Entity
public class ItcBALUSR {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cess_bal")
	private Double cessBAL;

	@JsonProperty("cgst_bal")
	private Double cgstBAL;

	@JsonProperty("igst_bal")
	private Double igstBAL;

	@JsonProperty("sgst_bal")
	private Double sgstBAL;

}