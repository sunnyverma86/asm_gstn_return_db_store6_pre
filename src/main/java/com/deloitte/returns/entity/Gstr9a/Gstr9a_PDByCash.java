package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "pd_by_cash", schema = "gstr9a")
@Entity
public class Gstr9a_PDByCash {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("debit_id")
	private String debitID;

	@JsonProperty("liab_id")
	private long liabID;

	@JsonProperty("trancd")
	private long trancd;

	@JsonProperty("trandate")
	private String trandate;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Gstr9a_Sgst sgst;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Gstr9a_Cgst cgst;

}