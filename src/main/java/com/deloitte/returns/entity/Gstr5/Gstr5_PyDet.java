
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "i_int", "c_int", "s_int", "cs_int", "c_lfee", "s_lfee" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "py_det", schema = "gstr5")
@Data
public class Gstr5_PyDet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Igst Intrest (Required)
	 * 
	 */
	@JsonProperty("i_int")
	@JsonPropertyDescription("Igst Intrest")
	@NotNull
	@Column
	public Double iInt;
	/**
	 * Cgst Intrest (Required)
	 * 
	 */
	@JsonProperty("c_int")
	@JsonPropertyDescription("Cgst Intrest")
	@NotNull
	@Column
	public Double cInt;
	/**
	 * Sgst Intrest (Required)
	 * 
	 */
	@JsonProperty("s_int")
	@JsonPropertyDescription("Sgst Intrest")
	@NotNull
	@Column
	public Double sInt;
	/**
	 * Cess Intrest (Required)
	 * 
	 */
	@JsonProperty("cs_int")
	@JsonPropertyDescription("Cess Intrest")
	@NotNull
	@Column
	public Double csInt;
	/**
	 * Cgst Latefee (Required)
	 * 
	 */
	@JsonProperty("c_lfee")
	@JsonPropertyDescription("Cgst Latefee")
	@NotNull
	@Column
	public Double cLfee;
	/**
	 * Sgst Latefee (Required)
	 * 
	 */
	@JsonProperty("s_lfee")
	@JsonPropertyDescription("Sgst Latefee")
	@NotNull
	@Column
	public Double sLfee;

	private final static long serialVersionUID = 4531289640028145113L;

}
