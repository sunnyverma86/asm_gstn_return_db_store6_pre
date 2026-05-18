package com.deloitte.returns.entity.Gstr1A;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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
@Table(name = "data", schema = "gstr1a")
@Entity
public class Gstr1A_Datum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("desc")
	@Column(length = 20000)
	private String descData;

	@JsonProperty("hsn_sc")
	@Column(length = 500)
	private String hsnSc;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("num")
	private Long num;

	@JsonProperty("qty")
	private Double qty;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("uqc")
	@Column(length = 500)
	private String uqc;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("rt")
	private Double rt;

}
