package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

import java.time.LocalDate;

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
@Table(name = "bzsdtls", schema = "demand_order_drc07")
public class DEMANDDTLS_Bzsdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("saccd")
	private String saccd;

	@JsonProperty("sdes")
	private String sdes;

}