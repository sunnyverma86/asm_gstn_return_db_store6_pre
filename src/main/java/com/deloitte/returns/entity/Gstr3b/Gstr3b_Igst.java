
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
@Table(name = "igst", schema = "gstr3b")
public class Gstr3b_Igst implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * IGST INTEREST Payable
	 * 
	 */
	@JsonProperty("intr")
	@JsonPropertyDescription("IGST INTEREST Payable")
	@Column
	private double intr;
	/**
	 * IGST Tax Payable
	 * 
	 */
	@JsonProperty("tx")
	@JsonPropertyDescription("IGST Tax Payable")
	@Column
	private double tx;

	/**
	 * IGST Fee Payable
	 *
	 */
	@JsonProperty("fee")
	@JsonPropertyDescription("IGST fee Payable")
	@Column
	private double fee;

	private final static long serialVersionUID = -1552577862127852113L;

}
