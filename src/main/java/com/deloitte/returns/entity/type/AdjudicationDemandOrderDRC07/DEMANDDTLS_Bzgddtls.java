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
@Table(name = "bzgddtls", schema = "demand_order_drc07")
	
public class DEMANDDTLS_Bzgddtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gdes")
	private String gdes;

	@JsonProperty("hsncd")
	private String hsncd;

}