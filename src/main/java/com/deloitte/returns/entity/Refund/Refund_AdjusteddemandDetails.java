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
@Table(name = "adjusteddemandDetails", schema = "refund")
public class Refund_AdjusteddemandDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("demandid")
	private String demandid;

	@JsonProperty("orderid")
	private String orderid;

	@JsonProperty("order_gross_cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_gross_cess_id")
	private Refund_OrderGrossCess orderGrossCess;

	@JsonProperty("order_gross_sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_gross_sgst_id")
	private Refund_OrderGrossSgst orderGrossSgst;

	@JsonProperty("order_gross_igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_gross_igst_id")
	private Refund_OrderGrossIgst orderGrossIgst;

	@JsonProperty("order_gross_cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_gross_cgst_id")
	private Refund_OrderGrossCgst orderGrossCgst;

}
