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
@Table(name = "pmtordervo", schema = "refund")
public class Refund_Pmtordervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("remarks")
	@Column(length = 500)
    private String remarks;

	@JsonProperty("rfdsanproorderno")
	private String rfdsanproorderno;

	@JsonProperty("rejamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rejamtdtl_id")
	private Refund_Rejamtdtl rejamtdtl;

	@JsonProperty("declaration")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pmtordervo_id")
	private List<Refund_Declaration> declaration;

}