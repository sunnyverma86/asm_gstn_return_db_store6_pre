package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Interest
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
@Table(name = "intr", schema = "gstr9")
@Entity
public class Gstr9_Intr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("txpaid_cash")
	private Double txpaidCash;

	@JsonProperty("txpyble")
	private Double txpyble;
	
	@JsonProperty("txpaid")
	private Double txpaid;
	
	@JsonProperty("diff_tax_paid") //new add
	private Double diff_tax_paid;
	
	@JsonProperty("total_tax_paid") //new add
	private Double total_tax_paid;


}