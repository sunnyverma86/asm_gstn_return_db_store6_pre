
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cfs", "ctin", "gstr5Inv" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "b2b", schema = "gstr5")
@Data
public class Gstr5_B2b implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * counter party filing status (Required)
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("counter party filing status")
	@NotNull
	@Column
	public String cfs;
	/**
	 * GSTIN/UID of the Receiver taxpayer/UN,Govt Bodies (Required)
	 * 
	 */
	@JsonProperty("ctin")
	@JsonPropertyDescription("GSTIN/UID of the Receiver taxpayer/UN,Govt Bodies")
	@NotNull
	@Column
	public String ctin;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("gstr5Inv")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	public List<Gstr5_Inv> gstr5Inv = new ArrayList<Gstr5_Inv>();
	private final static long serialVersionUID = -5398648638505979781L;

}
