
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
 * Interest
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "camt", "csamt", "iamt", "samt" })

@Entity
@Data
@Table(name = "intr_details", schema = "gstr3b")
public class Gstr3b_IntrDetails implements Serializable {
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
	 * CESS amount
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("CESS amount")
	@Column
	private double csamt;
	/**
	 * IGST Paid
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST Paid")
	@Column
	private double iamt;
	/**
	 * SGST amount
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST amount")
	@Column
	private double samt;

	private final static long serialVersionUID = 1020866160873350046L;

}
