
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "txval" })

@Entity
@Data
@Table(name = "eco_reg_sup", schema = "gstr3b")
public class Gstr3b_EcoRegSup implements Serializable {
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

	@JsonProperty("iamt")
	@NotNull
	@Column
	private double iamt;

	@JsonProperty("camt")
	@NotNull
	@Column
	private double camt;
	@JsonProperty("samt")
	@NotNull
	@Column
	private double samt;

	@JsonProperty("csamt")
	@NotNull
	@Column
	private double csamt;

	@OneToOne
	@JoinColumn(name = "eco_dtls_id")
	private Gstr3b_EcoDtls ecoDtls;

	private final static long serialVersionUID = 6695288748662897331L;

}
