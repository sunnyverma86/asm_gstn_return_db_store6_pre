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
@Table(name = "invoiceDtls", schema = "refund")
public class Refund_InvoiceDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("invoiceValue")
	private long invoiceValue;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("invoiceNo")
	private String invoiceNo;

	@JsonProperty("invoiceDate")
	private String invoiceDate;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("igst")
	private long igst;

}
