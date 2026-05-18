package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.*;

/**
 * Total demand of taxes
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
@Table(name = "tax_dmnd", schema = "gstr9")
@Entity
public class Gstr9_TaxDmnd {

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
	
	@JsonProperty("fee")
	private Double fee;
	
	@JsonProperty("pen")
	private Double pen;
	

}