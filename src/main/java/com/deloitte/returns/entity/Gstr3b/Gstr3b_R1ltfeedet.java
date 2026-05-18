
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
@JsonPropertyOrder({ "camt", "samt", "retprd" })

@Entity
@Data
@Table(name = "r1ltfeedet", schema = "gstr3b")
public class Gstr3b_R1ltfeedet implements Serializable {
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
	 * return period
	 * 
	 */
	@JsonProperty("retprd")
	@JsonPropertyDescription("return period")
	@Column
	private String retprd;

	private final static long serialVersionUID = 862168472094713737L;

}
