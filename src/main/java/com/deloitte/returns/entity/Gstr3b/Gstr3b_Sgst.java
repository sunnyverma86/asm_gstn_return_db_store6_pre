
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
@JsonPropertyOrder({ "intr", "tx", "fee" })

@Entity
@Data
@Table(name = "sgst", schema = "gstr3b")
public class Gstr3b_Sgst implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * SGST INTEREST Payable
	 * 
	 */
	@JsonProperty("intr")
	@JsonPropertyDescription("SGST INTEREST Payable")
	@Column
	private double intr;
	/**
	 * SGST Tax Payable
	 * 
	 */
	@JsonProperty("tx")
	@JsonPropertyDescription("SGST Tax Payable")
	@Column
	private double tx;
	/**
	 * SGST LateFee Payable
	 * 
	 */
	@JsonProperty("fee")
	@JsonPropertyDescription("SGST LateFee Payable")
	@Column
	private double fee;

	private final static long serialVersionUID = -1643143615072861323L;

}
