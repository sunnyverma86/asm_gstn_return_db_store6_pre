package com.deloitte.returns.entity.type.LedgerLiability;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ledger_liability", schema = "ledger_liability")
public class LedgerLiability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("fr_dt")
	private String frDt;

	@JsonProperty("to_dt")
	private String toDt;

	@JsonProperty("op_bal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "op_bal_id")
	private LedgerLiability_OpBAL opBAL;

	@JsonProperty("cl_bal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cl_bal_id")
	private LedgerLiability_ClBAL clBAL;

	@JsonProperty("tr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ledger_liability_id")
	private List<LedgerLiability_Tr> tr;
	
	


}