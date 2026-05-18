package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "pd_by_cash", schema = "gstr4")
@Entity
public class Gstr4_PdByCash {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("liab_id")
	private long liabID;

	@JsonProperty("trandate")
	private String trandate;

	@JsonProperty("trancd")
	private long trancd;

	@JsonProperty("val")
	private double val;

	@JsonProperty("desc")
	private String descData;

	@JsonProperty("tax_val")
	private Double taxVal;

	@JsonProperty("debit_id")
	private String debitID;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Gstr4_Sgst sgst;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Gstr4_Cgst cgst;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private Gstr4_Cess cess;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private Gstr4_Igst igst;

}
