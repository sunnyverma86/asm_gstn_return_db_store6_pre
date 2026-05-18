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
@Table(name = "table4", schema = "pmt")
public class Pmt_Table4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private Pmt_TaxPaid taxPaid;

	@JsonProperty("tax_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_pay_id")
	private Pmt_TaxPay taxPay;

}