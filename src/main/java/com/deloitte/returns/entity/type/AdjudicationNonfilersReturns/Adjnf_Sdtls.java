package com.deloitte.returns.entity.type.AdjudicationNonfilersReturns;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sdtls", schema = "adjudication_nonfilers_returns")
public class Adjnf_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjnf_Todtls todtls;

	@JsonProperty("drprcnf")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcnf_id")
	private Adjnf_Drprcnf drprcnf;

	@JsonProperty("nforder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nforder_id")
	private Adjnf_Nforder nforder;

}