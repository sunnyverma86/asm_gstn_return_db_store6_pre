package com.deloitte.returns.entity.Cmp8;

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
@Table(name = "table3", schema = "cmp08")
public class Cmp8_Table3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("in_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "in_sup_id")
	private Cmp8_InSup inSup;

	@JsonProperty("intr_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_pay_id")
	private Cmp8_IntrPay intrPay;

	@JsonProperty("out_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "out_sup_id")
	private Cmp8_OutSup outSup;

	@JsonProperty("tax_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_pay_id")
	private Cmp8_TaxPay taxPay;

}