package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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
@Table(name = "other", schema = "gstr9")
@Entity
public class Gstr9_Other {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("txpaid_cash")
	private Double txpaidCash;

	@JsonProperty("txpyble")
	private Double txpyble;
	
	@JsonProperty("desc")
	@Column(length = 1000)
	private String descOther;
	
	@JsonProperty("diff_tax_paid") //new add
	private Double diff_tax_paid;
	
	@JsonProperty("total_tax_paid") //new add
	private Double total_tax_paid;


	

}