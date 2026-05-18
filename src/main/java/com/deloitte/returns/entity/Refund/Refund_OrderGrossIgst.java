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
@Table(name = "order_gross_igst", schema = "refund")
public class Refund_OrderGrossIgst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("demand_adjusted_cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "demand_adjusted_cgst_id")
	private Refund_DemandAdjustedCgst demandAdjustedCgst;

	@JsonProperty("demand_adjusted_igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "demand_adjusted_igst_id")
	private Refund_DemandAdjustedIgst demandAdjustedIgst;

	@JsonProperty("demand_adjusted_sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "demand_adjusted_sgst_id")
	private Refund_DemandAdjustedSgst demandAdjustedSgst;

	@JsonProperty("demand_adjusted_cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "demand_adjusted_cess_id")
	private Refund_DemandAdjustedCess demandAdjustedCess;

}
