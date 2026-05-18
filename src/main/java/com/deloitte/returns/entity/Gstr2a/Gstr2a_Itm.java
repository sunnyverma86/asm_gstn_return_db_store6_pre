
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({ "num", "itm_det" })

@Entity
@Table(name = "itms", schema = "gstr2a")
@Data
public class Gstr2a_Itm implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * row numer (Required)
	 * 
	 */
	@JsonProperty("num")
	@JsonPropertyDescription("row numer")
	@Column
	public Integer num;
	/**
	 * details of the items of invoice (Required)
	 * 
	 */
	@JsonProperty("itm_det")
	@JsonPropertyDescription("details of the items of invoice")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "itmDet_id")
	public Gstr2a_ItmDet gstr2aItmDet;

	private final static long serialVersionUID = -5830394233890163822L;

}
