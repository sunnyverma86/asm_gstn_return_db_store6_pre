package com.deloitte.returns.entity.Gstr10;

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
@Table(name = "itms_sum", schema = "gstr10")
@Entity
public class Gstr10_ItmsSum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("csgst")
	private Double csgst;

	@JsonProperty("desc")
	private String descItmsSum;

	@JsonProperty("goods_ty")
	private String goodsTy;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("num")
	private double num;

	@JsonProperty("qty")
	private Double qty;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("uqc")
	private String uqc;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("flag")
	private String flag;

}