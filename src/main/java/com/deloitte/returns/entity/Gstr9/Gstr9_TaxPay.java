package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "tax_pay", schema = "gstr9")
@Entity
public class Gstr9_TaxPay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("liab_id")
	private Long liabID;

	@JsonProperty("trancd")
	private Long trancd;

	@JsonProperty("trandate")
	private String trandate;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private Gstr9_Cess cess;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Gstr9_Cgst cgst;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private Gstr9_Igst igst;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Gstr9_Sgst sgst;

}