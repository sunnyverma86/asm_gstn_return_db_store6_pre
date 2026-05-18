
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
@Table(name = "itc_inelg", schema = "gstr3b")
public class Gstr3b_ItcInelg implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Amount of ITC reversal of IGST
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("Amount of ITC reversal of IGST ")
	@Column
	private double iamt;
	/**
	 * Amount of CTC reversal of IGST
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("Amount of CTC reversal of IGST ")
	@Column
	private double camt;
	/**
	 * Amount of STC reversal of IGST
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("Amount of STC reversal of IGST ")
	@Column
	private double samt;
	/**
	 * Amount of cess reversal of IGST
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("Amount of cess reversal of IGST ")
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

	private final static long serialVersionUID = -6243037914170776699L;

}
