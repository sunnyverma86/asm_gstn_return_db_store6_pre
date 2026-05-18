package com.deloitte.returns.entity.Cmp8;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "in_sup", schema = "cmp08")
public class Cmp8_InSup {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("camt")
	private double camt;

	@JsonProperty("csamt")
	private double csamt;

	@JsonProperty("iamt")
	private double iamt;

	@JsonProperty("samt")
	private double samt;

	@JsonProperty("tax_val")
	private double taxVal;
}
