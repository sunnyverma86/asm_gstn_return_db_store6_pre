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
@Table(name = "itms", schema = "gstr10")
@Entity
public class Gstr10_Itms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("val")
	private long val;

	@JsonProperty("csgst")
	private long csgst;

	@JsonProperty("goods_ty")
	private String goodsTy;

	@JsonProperty("uqc")
	private String uqc;

	@JsonProperty("qty")
	private long qty;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("igst")
	private double igst;

	@JsonProperty("desc")
	private String descItms;

}
