package com.deloitte.returns.entity.Gstr10;

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
@Table(name = "cgst", schema = "gstr10")
@Entity
public class Gstr10_Cgst {

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