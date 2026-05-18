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
@Table(name = "comamtdtl", schema = "refund")
public class Refund_Comamtdtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("adjamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adjamt_id")
	private Refund_Adjamt adjamt;

	@JsonProperty("admamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admamt_id")
	private Refund_Admamt admamt;

	@JsonProperty("balncamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "balncamt_id")
	private Refund_Balncamt balncamt;

	@JsonProperty("clmamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clmamt_id")
	private Refund_Clmamt clmamt;

	@JsonProperty("netprosancamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "netprosancamt_id")
	private Refund_Netprosancamt netprosancamt;

}