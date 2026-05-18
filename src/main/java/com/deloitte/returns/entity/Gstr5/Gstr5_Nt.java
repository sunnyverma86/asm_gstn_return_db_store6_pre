
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
@JsonPropertyOrder({ "cflag", "chksum", "flag", "idt", "inum", "diff_percent", "gstr5Itms", "nt_dt", "nt_num", "ont_dt",
		"ont_num", "ntty", "opd", "updby", "val" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "nt", schema = "gstr5")
@Data
public class Gstr5_Nt implements Serializable {

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
	@JsonPropertyDescription("Invoice checksum value")
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
	@JoinColumn(name = "nt_id")
	public List<Gstr5_Itm> gstr5Itms = new ArrayList<Gstr5_Itm>();
	/**
	 * Note Date (Required)
	 * 
	 */
	@JsonProperty("nt_dt")
	@JsonPropertyDescription("Note Date")
	@NotNull
	@Column
	public String ntDt;
	/**
	 * Note Number (Required)
	 * 
	 */
	@JsonProperty("nt_num")
	@JsonPropertyDescription("Note Number")
	@NotNull
	@Column
	public String ntNum;
	/**
	 * Original Note Date (Required)
	 * 
	 */
	@JsonProperty("ont_dt")
	@JsonPropertyDescription("Original Note Date")
	@NotNull
	@Column
	public String ontDt;
	/**
	 * Original Note Number (Required)
	 * 
	 */
	@JsonProperty("ont_num")
	@JsonPropertyDescription("Original Note Number")
	@NotNull
	@Column
	public String ontNum;
	/**
	 * Note Type (Required)
	 * 
	 */
	@JsonProperty("ntty")
	@JsonPropertyDescription("Note Type")
	@NotNull
	@Column
	public String ntty;
	/**
	 * Original period
	 * 
	 */
	@JsonProperty("opd")
	@JsonPropertyDescription("Original period")
	@Column
	public String opd;
	/**
	 * uploaded by (Required)
	 * 
	 */
	@JsonProperty("updby")
	@JsonPropertyDescription("uploaded by")
	@NotNull
	@Column
	public String updby;
	/**
	 * Note Value (Required)
	 * 
	 */
	@JsonProperty("val")
	@JsonPropertyDescription("Note Value")
	@NotNull
	@Column
	public Double val;
	private final static long serialVersionUID = -2295905843805623381L;

}
