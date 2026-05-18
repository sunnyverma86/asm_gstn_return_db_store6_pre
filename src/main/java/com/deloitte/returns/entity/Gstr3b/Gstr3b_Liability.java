
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
@JsonPropertyOrder({ "iamt", "camt", "samt", "csamt" })

@Entity
@Data
@Table(name = "liability", schema = "gstr3b")
public class Gstr3b_Liability implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Declared IGST liability
	 * 
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("Declared IGST liability")
	@Column
	public Double iamt;
	/**
	 * Declared CGST liability
	 * 
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("Declared CGST liability")
	@Column
	public Double camt;
	/**
	 * Declared SGST liability
	 * 
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("Declared SGST liability")
	@Column
	public Double samt;
	/**
	 * Declared Cess liability
	 * 
	 */
	@JsonProperty("csamt")
	@JsonPropertyDescription("Declared Cess liability ")
	@Column
	public Double csamt;

	private final static long serialVersionUID = -3162744451646896644L;

}
