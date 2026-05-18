package com.deloitte.returns.entity.type.LedgerItc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provCrdBal", schema = "ledger_itc")
public class LedgerItc_ProvCrdBal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
