package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1;

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
@Entity
@Table(name = "cpins", schema = "enforcement_officer_RSP")
public class ENFRECSRCH_Cpins {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("total_amt")
	private String totalAmt;

	@JsonProperty("create_dt")
	private String createDt;

	@JsonProperty("cin")
	private String cin;

	@JsonProperty("payment_dt")
	private String paymentDt;

	@JsonProperty("cpin")
	private String cpin;

}