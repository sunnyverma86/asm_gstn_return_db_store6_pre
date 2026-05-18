package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.*;

/**
 * Deemed supply under Section 143
 */

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
@Table(name = "deemed_supp", schema = "gstr9")
@Entity
public class Gstr9_DeemedSupp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;
	
	@JsonProperty("intr")
	private Double intr;
}