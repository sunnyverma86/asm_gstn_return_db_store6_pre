
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
@JsonPropertyOrder({ "cflag", "chksum", "flag", "idt", "inum", "oidt", "oinum", "diff_percent", "itms", "opd", "pos",
		"updby", "val" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "inv", schema = "gstr5")
@Data
public class Gstr5_Inv implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Counter Party Flag (Required)
	 * 
	 */
	@JsonProperty("cflag")
	@JsonPropertyDescription("Counter Party Flag")
	@NotNull
	@Column
	public String cflag;
	/**
	 * Invoice checksum value (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@JsonPropertyDescription(" Invoice checksum value")
	@NotNull
	@Column
	public String chksum;
	/**
	 * flag for accepting or rejecting a invoice (Required)
	 * 
	 */
	@JsonProperty("flag")
	@JsonPropertyDescription("flag for accepting or rejecting a invoice")
	@NotNull
	@Column
	public String flag;
	/**
	 * Supplier Invoice Date (Required)
	 * 
	 */
	@JsonProperty("idt")
	@JsonPropertyDescription("Supplier Invoice Date")
	@NotNull
	@Column
	public String idt;
	/**
	 * Supplier Invoice Number (Required)
	 * 
	 */
	@JsonProperty("inum")
	@JsonPropertyDescription("Supplier Invoice Number")
	@NotNull
	@Column
	public String inum;
	/**
	 * Original Supplier Invoice Date (Required)
	 * 
	 */
	@JsonProperty("oidt")
	@JsonPropertyDescription("Original Supplier Invoice Date")
	@NotNull
	@Column
	public String oidt;
	/**
	 * Original Supplier Invoice Number (Required)
	 * 
	 */
	@JsonProperty("oinum")
	@JsonPropertyDescription("Original Supplier Invoice Number")
	@NotNull
	@Column
	public String oinum;
	/**
	 * Differential percentage
	 * 
	 */
	@JsonProperty("diff_percent")
	@JsonPropertyDescription("Differential percentage")
	@Column
	public Double diffPercent;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("itms")
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_id")
	public List<Gstr5_Itm> gstr5Itms = new ArrayList<Gstr5_Itm>();
	/**
	 * Original Period
	 * 
	 */
	@JsonProperty("opd")
	@JsonPropertyDescription("Original Period")
	@Column
	public String opd;
	/**
	 * place of supply
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("place of supply")
	@Column
	public String pos;
	/**
	 * Invoice uploaded by (Required)
	 * 
	 */
	@JsonProperty("updby")
	@JsonPropertyDescription(" Invoice uploaded by")
	@NotNull
	@Column
	public String updby;
	/**
	 * Supplier Invoice Value (Required)
	 * 
	 */
	@JsonProperty("val")
	@JsonPropertyDescription("Supplier Invoice Value")
	@NotNull
	@Column
	public Double val;
	private final static long serialVersionUID = -1618435620960321239L;

}
