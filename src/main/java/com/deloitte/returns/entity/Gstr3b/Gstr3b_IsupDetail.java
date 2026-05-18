
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
@JsonPropertyOrder({ "inter", "intra", "ty" })

@Entity
@Data
@Table(name = "isup_detail", schema = "gstr3b")
public class Gstr3b_IsupDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Inter State supplies
	 * 
	 */
	@JsonProperty("inter")
	@JsonPropertyDescription("Inter State supplies")
	@Column
	private double inter;
	/**
	 * Intra State supplies
	 * 
	 */
	@JsonProperty("intra")
	@JsonPropertyDescription("Intra State supplies")
	@Column
	private double intra;
	/**
	 * Identifer if Goods or Services
	 * 
	 */
	@JsonProperty("ty")
	@JsonPropertyDescription("Identifer if Goods or Services")
	@Column
	private String ty;

	private final static long serialVersionUID = 4929940127819124603L;

}
