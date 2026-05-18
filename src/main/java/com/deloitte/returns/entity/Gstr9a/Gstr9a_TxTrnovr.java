package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "tx_trnovr", schema = "gstr9a")
@Entity
public class Gstr9a_TxTrnovr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("rt")
	private long rt;

	@JsonProperty("trnovr")
	private long trnovr;

	@JsonProperty("camt")
	private long camt;

	@JsonProperty("csamt")
	private long csamt;

	@JsonProperty("iamt")
	private long iamt;

	@JsonProperty("samt")
	private long samt;

	@JsonProperty("fee")
	private long fee;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("pen")
	private long pen;

	@JsonProperty("tot")
	private long tot;

	@JsonProperty("tx")
	private long tx;
	
	@JsonProperty("txval")
	private long txval;

}