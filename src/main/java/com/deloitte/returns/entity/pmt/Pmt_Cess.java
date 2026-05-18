package com.deloitte.returns.entity.pmt;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cess", schema = "pmt")
public class Pmt_Cess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fee")
	private double fee;

	@JsonProperty("intr")
	private double intr;

	@JsonProperty("oth")
	private double oth;

	@JsonProperty("pen")
	private double pen;

	@JsonProperty("tot")
	private double tot;

	@JsonProperty("tx")
	private double tx;

}