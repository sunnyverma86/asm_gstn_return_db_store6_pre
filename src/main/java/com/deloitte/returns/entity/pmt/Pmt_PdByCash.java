package com.deloitte.returns.entity.pmt;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pd_by_cash", schema = "pmt")
public class Pmt_PdByCash {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("debit_id")
	private double debitID;

	@JsonProperty("liab_id")
	private double liabID;

	@JsonProperty("trancd")
	private double trancd;

	@JsonProperty("trancd")
	private String trandate;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private Pmt_Cess cess;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Pmt_Cgst cgst;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private Pmt_Igst igst;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Pmt_Sgst sgst;

}