package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Integrated Tax
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
@Table(name = "igst", schema = "gstr9")
@Entity
public class Gstr9_Igst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fee")
	private Double fee;

	@JsonProperty("intr")
	private Double intr;

	@JsonProperty("oth")
	private Double oth;

	@JsonProperty("pen")
	private Double pen;

	@JsonProperty("tot")
	private Double tot;

	@JsonProperty("tx")
	private Double tx;
}