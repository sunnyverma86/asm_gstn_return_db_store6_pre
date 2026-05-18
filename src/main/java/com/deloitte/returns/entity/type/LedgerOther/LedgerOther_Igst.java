package com.deloitte.returns.entity.type.LedgerOther;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "igst", schema = "ledger_other")
public class LedgerOther_Igst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("prov")
	private long prov;

	@JsonProperty("conf")
	private long conf;

	@JsonProperty("tot")
	private long tot;

	@JsonProperty("tx")
	private long tx;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("pen")
	private long pen;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("fee")
	private long fee;
}
