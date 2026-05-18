
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

/**
 * Latefee
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "camt", "samt", "iamt", "csamt" })

@Entity
@Data
@Table(name = "ltfee_details", schema = "gstr3b")
public class Gstr3b_LtfeeDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * CGST amount
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST amount")
	@Column
	private double camt;
	/**
	 * SGST amount
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST amount")
	@Column
	private double samt;

	/**
	 * integrated Taxamount available in the current month
	 *
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("integrated Taxamount available in the current month")
	@Column
	private double iamt;
	/**
	 * Cess Tax amount available in the current month
	 *
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("Cess Tax amount available in the current month")
	@Column
	private double csamt;

	private final static long serialVersionUID = 4567697021714097022L;

}
