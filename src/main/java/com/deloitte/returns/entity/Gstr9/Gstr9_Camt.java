package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Central Tax
 */

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
@Table(name = "camt", schema = "gstr9")
@Entity
public class Gstr9_Camt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("tax_paid_itc_camt")
	private Double taxPaidItcCamt;

	@JsonProperty("tax_paid_itc_iamt")
	private Double taxPaidItcIamt;

	@JsonProperty("txpaid_cash")
	private Double txpaidCash;

	@JsonProperty("txpyble")
	private Double txpyble;
	
	@JsonProperty("txpaid")
	private Double txpaid;
	
	@JsonProperty("intr")
	private Double intr;

	@JsonProperty("diff_tax_paid") //new add
	private Double diff_tax_paid;
	
	@JsonProperty("total_tax_paid") //new add
	private Double total_tax_paid;
}