package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "proamtdtl", schema = "refund")
public class Refund_Proamtdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("balncamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "balncamt_id")
	private Refund_Balncamt balncamt;

	@JsonProperty("clmamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clmamt_id")
	private Refund_Clmamt clmamt;

	@JsonProperty("provamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provamt_id")
	private Refund_Provamt provamt;

	@JsonProperty("provper")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provper_id")
	private Refund_Provper provper;

	@JsonProperty("sancamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancamt_id")
	private Refund_Sancamt sancamt;

}