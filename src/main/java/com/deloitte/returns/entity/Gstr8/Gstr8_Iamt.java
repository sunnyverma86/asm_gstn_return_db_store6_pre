
package com.deloitte.returns.entity.Gstr8;

import java.io.Serializable;

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

/**
 * IGST amount payable
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tx", "intr", "pen", "fee", "oth", "tot" })

@Entity
@Table(name = "iamt", schema = "gstr8")
@Data
public class Gstr8_Iamt implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tx")
	@Column
	public Double tx;

	@JsonProperty("intr")
	@Column
	public Double intr;

	@JsonProperty("pen")
	@Column
	public Double pen;

	@JsonProperty("fee")
	@Column
	public Double fee;

	@JsonProperty("oth")
	@Column
	public Double oth;

	@JsonProperty("tot")
	@Column
	public Double tot;

	private final static long serialVersionUID = -7728064183558455762L;

}
