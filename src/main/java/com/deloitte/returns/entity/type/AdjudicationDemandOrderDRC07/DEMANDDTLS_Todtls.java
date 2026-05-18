package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

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
@Table(name = "todtls", schema = "demand_order_drc07")
	
public class DEMANDDTLS_Todtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("toid")
	private String toid;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("dg")
	private String dg;

	@JsonProperty("signty")
	private String signty;

	@JsonProperty("pn")
	private String pn;

	@JsonProperty("nm")
	private String nm;
	
	//////////////Add New////////////////
	
	@JsonProperty("pl")
	private String pl;
	
	

}