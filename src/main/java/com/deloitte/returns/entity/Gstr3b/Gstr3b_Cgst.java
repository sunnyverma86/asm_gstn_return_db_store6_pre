
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
@Table(name = "cgst", schema = "gstr3b")
public class Gstr3b_Cgst implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * CGST INTEREST Payable
	 * 
	 */
	@JsonProperty("intr")
	@JsonPropertyDescription("CGST INTEREST Payable")
	@Column
	private double intr;
	/**
	 * CGST Tax Payable
	 * 
	 */
	@JsonProperty("tx")
	@JsonPropertyDescription("CGST Tax Payable")
	@Column
	private double tx;
	/**
	 * CGST LateFee Payable
	 * 
	 */
	@JsonProperty("fee")
	@JsonPropertyDescription("CGST LateFee Payable")
	@Column
	private double fee;

	private final static long serialVersionUID = -7262415968808079702L;

}
