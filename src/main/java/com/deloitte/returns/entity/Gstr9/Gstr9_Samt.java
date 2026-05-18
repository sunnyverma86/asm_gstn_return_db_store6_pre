package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;


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
@Table(name = "samt", schema = "gstr9")
@Entity
public class Gstr9_Samt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("tax_paid_itc_iamt")
	private Double taxPaidItcIamt;

	@JsonProperty("tax_paid_itc_samt")
	private Double taxPaidItcSamt;

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