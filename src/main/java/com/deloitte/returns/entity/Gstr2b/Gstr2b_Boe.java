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
@Table(name = "boe", schema = "gstr2b")
@Entity
public class Gstr2b_Boe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("boedt")
	private String boedt;

	@JsonProperty("boenum")
	private String boenum;

	@JsonProperty("cess")
	private double cess;

	@JsonProperty("igst")
	private double igst;

	@JsonProperty("cgst")
	private double cgst;

	@JsonProperty("sgst")
	private double sgst;

	@JsonProperty("isamd")
	private String isamd;

	@JsonProperty("portcode")
	private String portcode;

	@JsonProperty("recdt")
	private String recdt;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("imsStatus")
	private String imsStatus;

	@JsonProperty("txval")
	private double txval;

}