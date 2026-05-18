package com.deloitte.returns.entity.Gstr2b;

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
@Table(name = "impgasez", schema = "gstr2b")
@Entity
public class Gstr2b_Impgasez {

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

	@JsonProperty("num")
	private double num;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("txval")
	private double txval;
	
	@JsonProperty("ctin")
	private String ctin;
	
	@JsonProperty("trdnm")
	private String trdnm;
	
	@JsonProperty("portcode")
	private String portcode;
	
	@JsonProperty("ttldocs")
	private double ttldocs;

}