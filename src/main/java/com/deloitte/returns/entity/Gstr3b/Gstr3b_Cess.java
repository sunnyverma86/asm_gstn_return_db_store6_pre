
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "intr", "tx" })

@Entity
@Data
@Table(name = "cess", schema = "gstr3b")
public class Gstr3b_Cess implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * CESS INTEREST Payable
	 * 
	 */
	@JsonProperty("intr")
	@JsonPropertyDescription("CESS INTEREST Payable")
	@Column
	private double intr;
	/**
	 * CESS Tax Payable
	 * 
	 */
	@JsonProperty("tx")
	@JsonPropertyDescription("CESS Tax Payable")
	@Column
	private double tx;

	/**
	 * CESS Fee Payable
	 *
	 */
	@JsonProperty("fee")
	@JsonPropertyDescription("CESS fee Payable")
	@Column
	private double fee;

	private final static long serialVersionUID = 8342460626042164215L;

}
