package com.deloitte.returns.entity.Refund;

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
@Table(name = "demand_adjusted_cess", schema = "refund")
public class Refund_DemandAdjustedCess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("fee")
	private long fee;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("pen")
	private long pen;

	@JsonProperty("tax")
	private long tax;

	@JsonProperty("tot")
	private long tot;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("igst")
	private double igst;

}
