package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

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
@Table(name = "tp ", schema = "demand_order_drc07")
public class DEMANDDTLS_Tp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fromm")
	private String fromm;

	@JsonProperty("tom")
	private String tom;

	@JsonProperty("fromy")
	private String fromy;

	@JsonProperty("toy")
	private String toy;

}