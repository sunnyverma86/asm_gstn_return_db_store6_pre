package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stmnt7edt", schema = "refund")
public class Refund_Stmnt7Edt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("retType")
	private String retType;

	@JsonProperty("taxPeriod")
	private String taxPeriod;

	@JsonProperty("retFilDate")
	private String retFilDate;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("igst")
	private long igst;

}
