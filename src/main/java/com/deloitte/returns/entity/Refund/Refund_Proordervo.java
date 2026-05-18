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
@Table(name = "proordervo", schema = "refund")
public class Refund_Proordervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("proamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "proamtdtl_id")
	private Refund_Proamtdtl proamtdtl;

}