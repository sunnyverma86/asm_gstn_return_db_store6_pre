package com.deloitte.returns.entity.Refund;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
@Table(name = "comordervo", schema = "refund")
public class Refund_Comordervo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("comamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comamtdtl_id")
	private Refund_Comamtdtl comamtdtl;

	@JsonProperty("reasonselectionCom")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comordervo_id")
	private List<Refund_ReasonselectionCOM> reasonselectionCOM;

}
