package com.deloitte.returns.entity.type.LedgerItc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provCrdBalList", schema = "ledger_itc")
public class LedgerItc_ProvCrdBalList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("provCrdBal")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "provCrdBal_id")
	private List<LedgerItc_ProvCrdBal> provCrdBal;

}
