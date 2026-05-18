package com.deloitte.returns.entity.Gstr2b;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imports", schema = "gstr2b")
@Entity
public class Gstr2b_Imports {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("impg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "impg_id")
	private Gstr2b_Impg impg;

	@JsonProperty("impga")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "impga_id")
	private Gstr2b_Impga impga;

	@JsonProperty("impgasez")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "impgasez_id")
	private Gstr2b_Impgasez impgasez;

	@JsonProperty("impgsez")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "impgsez_id")
	private Gstr2b_Impgsez impgsez;

}