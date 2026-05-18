
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
@JsonPropertyOrder({ "iamt", "camt", "samt", "csamt", "ty" })

@Entity
@Data
@Table(name = "itc_avl", schema = "gstr3b")
public class Gstr3b_ItcAvl implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * integrated Taxamount available in the current month
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("integrated Taxamount available in the current month")
	@Column
	private double iamt;
	/**
	 * CGST Amount as per invoice
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST Amount as per invoice")
	@Column
	private double camt;
	/**
	 * SGST Amount as per invoice
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST Amount as per invoice")
	@Column
	private double samt;
	/**
	 * Cess Tax amount available in the current month
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("Cess Tax amount available in the current month")
	@Column
	private double csamt;
	/**
	 * Identifer if Goods or Services
	 * 
	 */
	@JsonProperty("ty")
	@JsonPropertyDescription("Identifer if Goods or Services")
	@Column
	private String ty;

	private final static long serialVersionUID = -8446647990834956871L;

}
