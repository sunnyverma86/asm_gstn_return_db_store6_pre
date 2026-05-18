
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "txval", "iamt", "camt", "samt", "csamt" })

@Entity
@Data
@Table(name = "eco_sup", schema = "gstr3b")
public class Gstr3b_EcoSup implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("txval")
	@NotNull
	@Column
	private double txval;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("iamt")
	@NotNull
	@Column
	private double iamt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("camt")
	@NotNull
	@Column
	private double camt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("samt")
	@NotNull
	@Column
	private double samt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("csamt")
	@NotNull
	@Column
	private double csamt;

	private final static long serialVersionUID = 3942809046147960740L;

}
