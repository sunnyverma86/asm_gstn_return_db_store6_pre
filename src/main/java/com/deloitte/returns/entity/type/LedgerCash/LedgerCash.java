package com.deloitte.returns.entity.type.LedgerCash;

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
@Table(name = "ledger_cash", schema = "ledger_cash")
public class LedgerCash {

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
	private LedgerCash_OpBAL opBAL;

	@JsonProperty("cl_bal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cl_bal_id")
	private LedgerCash_ClBAL clBAL;

	@JsonProperty("tr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ledger_cash_id")
	private List<LedgerCash_Tr> tr;
	
	 

}