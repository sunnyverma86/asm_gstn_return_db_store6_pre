
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
@JsonPropertyOrder({ "chksum", "idt", "inum", "diff_percent", "gstr5Itms", "nt_dt", "nt_num", "ntty", "val" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "cdnur", schema = "gstr5")
@Data
public class Gstr5_Cdnur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@JsonProperty("gstr5Itms")
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnur_id")
	public List<Gstr5_Itm2> itms = new ArrayList<Gstr5_Itm2>();
	/**
	 * credit Date (Required)
	 * 
	 */
	@JsonProperty("nt_dt")
	@JsonPropertyDescription("credit Date")
	@NotNull
	@Column
	public String ntDt;
	/**
	 * Credit/Debit Note Number (Required)
	 * 
	 */
	@JsonProperty("nt_num")
	@JsonPropertyDescription("Credit/Debit Note Number")
	@NotNull
	@Column
	public String ntNum;
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
	 * Note Value (Required)
	 * 
	 */
	@JsonProperty("val")
	@JsonPropertyDescription("Note Value")
	@NotNull
	@Column
	public Double val;
	private final static long serialVersionUID = -8936432732501150132L;

}
