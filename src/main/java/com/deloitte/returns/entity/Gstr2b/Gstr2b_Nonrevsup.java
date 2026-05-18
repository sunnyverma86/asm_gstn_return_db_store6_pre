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
@Table(name = "nonrevsup", schema = "gstr2b")
@Entity
public class Gstr2b_Nonrevsup {

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

	@JsonProperty("b2b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private Gstr2b_B2B b2B;

	@JsonProperty("b2ba")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2ba_id")
	private Gstr2b_B2Ba b2Ba;

	@JsonProperty("cdnr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnr_id")
	private Gstr2b_Cdnr cdnr;

	@JsonProperty("cdnra")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnra_id")
	private Gstr2b_Cdnra cdnra;

	@JsonProperty("ecom")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private Gstr2b_Ecom ecom;

	@JsonProperty("ecoma")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private Gstr2b_Ecoma ecoma;

	@JsonProperty("txval")
	private Double txval;

}