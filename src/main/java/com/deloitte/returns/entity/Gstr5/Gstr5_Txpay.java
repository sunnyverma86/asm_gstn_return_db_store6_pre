
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
@JsonPropertyOrder({ "i_py", "c_py", "s_py", "cs_py" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "txpay", schema = "gstr5")
@Data
public class Gstr5_Txpay implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Igst Paybale (Required)
	 * 
	 */
	@JsonProperty("i_py")
	@JsonPropertyDescription("Igst Paybale")
	@NotNull
	@Column
	public Double iPy;
	/**
	 * Cgst Paybale (Required)
	 * 
	 */
	@JsonProperty("c_py")
	@JsonPropertyDescription("Cgst Paybale")
	@NotNull
	@Column
	public Double cPy;
	/**
	 * Sgst Paybale (Required)
	 * 
	 */
	@JsonProperty("s_py")
	@JsonPropertyDescription("Sgst Paybale")
	@NotNull
	@Column
	public Double sPy;
	/**
	 * Cess Paybale (Required)
	 * 
	 */
	@JsonProperty("cs_py")
	@JsonPropertyDescription("Cess Paybale")
	@NotNull
	@Column
	public Double csPy;

	private final static long serialVersionUID = -2770289566059079853L;

}
