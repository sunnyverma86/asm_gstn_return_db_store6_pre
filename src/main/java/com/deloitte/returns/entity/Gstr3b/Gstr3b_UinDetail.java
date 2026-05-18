
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
@JsonPropertyOrder({ "pos", "txval", "iamt" })

@Entity
@Data
@Table(name = "uin_detail", schema = "gstr3b")
public class Gstr3b_UinDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Place of supply
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("Place of supply")
	@Column
	private String pos;
	/**
	 * Taxable Value
	 * 
	 */
	@JsonProperty("txval")
	@JsonPropertyDescription("Taxable Value")
	@Column
	private double txval;
	/**
	 * IGST Amount as per invoice
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST Amount as per invoice")
	@Column
	private double iamt;

	private final static long serialVersionUID = -8730929326877939051L;

}
