
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
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
@JsonPropertyOrder({ "itm_det", "num" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "itm", schema = "gstr5")
@Data
public class Gstr5_Itm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("itm_det")
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itm_det_id")
	public Gstr5_ItmDet gstr5ItmDet;
	/**
	 * Serial Number (Required)
	 * 
	 */
	@JsonProperty("num")
	@JsonPropertyDescription("Serial Number")
	@NotNull
	@Column
	public Double num;
	private final static long serialVersionUID = 1733959132313743844L;

}
