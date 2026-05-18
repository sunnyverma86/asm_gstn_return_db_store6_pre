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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pmtsancordervo", schema = "refund")
public class Refund_Pmtsancordervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sancamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancamtdtl_id")
	private Refund_Sancamtdtl sancamtdtl;

	@JsonProperty("rfdsanproorderno")
	private String rfdsanproorderno;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("declaration")
	private List<String> declaration;
	
	@JsonProperty("remarks")
	@Column(length = 500)
	private String remarks;
	

}