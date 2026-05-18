
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
@JsonPropertyOrder({ "chksum", "omon", "pos", "supty", "diff_percent", "gstr5Itms" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "b2csa", schema = "gstr5")
@Data
public class Gstr5_B2csa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Checksum Value (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@JsonPropertyDescription("Checksum Value")
	@NotNull
	@Column
	public String chksum;
	/**
	 * Original Month (Required)
	 * 
	 */
	@JsonProperty("omon")
	@JsonPropertyDescription("Original Month")
	@NotNull
	@Column
	public String omon;
	/**
	 * Place Of Supply (Required)
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("Place Of Supply")
	@NotNull
	@Column
	public String pos;
	/**
	 * Supply Type (Required)
	 * 
	 */
	@JsonProperty("supty")
	@JsonPropertyDescription("Supply Type")
	@NotNull
	@Column
	public String supty;
	/**
	 * Differential percentage
	 * 
	 */
	@JsonProperty("diff_percent")
	@JsonPropertyDescription("Differential percentage")
	@Column
	public Double diffPercent;

	@JsonProperty("gstr5Itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2csa_id")
	public List<Gstr5_Itm2> itms = new ArrayList<Gstr5_Itm2>();

	private final static long serialVersionUID = 6114579781052306800L;

}
