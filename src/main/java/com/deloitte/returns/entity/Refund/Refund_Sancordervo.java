package com.deloitte.returns.entity.Refund;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sancordervo", schema = "refund")
public class Refund_Sancordervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("remarks")
	@Column(length = 500)
	private String remarks;

	@JsonProperty("sancamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancamtdtl_id")
	private Refund_Sancamtdtl sancamtdtl;

	@JsonProperty("reasonselection")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancordervo_id")
	private List<Refund_reasonselection> reasonselection;

	@JsonProperty("inadmreason")
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "inadmreason_id")
	private List<String> inadmreason;

	@JsonProperty("cwfflag")
	private boolean cwfflag;
	
	@JsonProperty("adjusteddemandDetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancordervo_id")
	private List<Refund_AdjusteddemandDetails> adjusteddemandDetails;
	
	@JsonProperty("riskexpremarks")
	private String riskexpremarks;
	

}