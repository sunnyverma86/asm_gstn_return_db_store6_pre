package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "itms", schema = "gstr4")
@Entity
public class Gstr4_Itms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ad_amt")
	private double adAmt;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("num")
	private long num;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("txval")
	private double txval;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("trnovr")
	private Double trnovr;

	@JsonProperty("itm_det")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itm_det_id")
	private Gstr4_ItmDet itmDet;

}
