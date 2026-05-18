
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
@JsonPropertyOrder({ "txval" })

@Entity
@Data
@Table(name = "osup_nongst", schema = "gstr3b")
public class Gstr3b_OsupNongst implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Taxable Value
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable Value")
	@Column
	private double txval;
	/**
	 * integrated Taxamount available in the current month
	 *
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("integrated Taxamount available in the current month")
	@Column
	private double iamt;
	@JsonProperty("camt")
	@JsonPropertyDescription("Central tax amount available in the current month")
	@Column
	private double camt;
	@JsonProperty("samt")
	@JsonPropertyDescription("State Tax amount available in the current month")
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

	private final static long serialVersionUID = -5498246062330973889L;

}
