package com.deloitte.returns.entity.EwayBill;

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
@Entity
@Table(name = "ExtendDet", schema = "eway_live_eway_bill_new")
public class EwayBill_ExtendDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ExtDt")
	private String extDt;

	@JsonProperty("ExtReasCd")
	private long extReasCD;

	@JsonProperty("ExtReasRem")
	private String extReasRem;

	@JsonProperty("ExtBy")
	private String extBy;

	@JsonProperty("FrPlace")
	private String frPlace;

	@JsonProperty("FrStat")
	private long frStat;

	@JsonProperty("RemDist")
	private long remDist;

	@JsonProperty("Prev_ValidDt")
	private String prevValidDt;

}
