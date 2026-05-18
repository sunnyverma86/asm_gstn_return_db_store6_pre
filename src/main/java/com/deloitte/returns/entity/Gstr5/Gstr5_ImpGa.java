
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
@JsonPropertyOrder({ "boe_dt", "boe_num", "oboe_dt", "oboe_num", "boe_val", "chksum", "diff_percent", "itms",
		"port_code", "oport_code" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "imp_ga", schema = "gstr5")
@Data
public class Gstr5_ImpGa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Bill of entry date (Required)
	 * 
	 */
	@JsonProperty("boe_dt")
	@JsonPropertyDescription("Bill of entry date")
	@NotNull
	@Column
	public String boeDt;
	/**
	 * Bill of entry number (Required)
	 * 
	 */
	@JsonProperty("boe_num")
	@JsonPropertyDescription("Bill of entry number")
	@NotNull
	@Column
	public String boeNum;
	/**
	 * Original Bill of entry date (Required)
	 * 
	 */
	@JsonProperty("oboe_dt")
	@JsonPropertyDescription("Original Bill of entry date")
	@NotNull
	@Column
	public String oboeDt;
	/**
	 * Original Bill of entry number (Required)
	 * 
	 */
	@JsonProperty("oboe_num")
	@JsonPropertyDescription("Original Bill of entry number")
	@NotNull
	@Column
	public String oboeNum;
	/**
	 * Bill of entry value (Required)
	 * 
	 */
	@JsonProperty("boe_val")
	@JsonPropertyDescription("Bill of entry value")
	@NotNull
	@Column
	public Double boeVal;
	/**
	 * Invoice Checksum value (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@JsonPropertyDescription("Invoice Checksum value")
	@NotNull
	@Column
	public String chksum;
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
	@JoinColumn(name = "imp_ga_id")
	public List<Gstr5_Itm1> itms = new ArrayList<Gstr5_Itm1>();
	/**
	 * Port code (Required)
	 * 
	 */
	@JsonProperty("port_code")
	@JsonPropertyDescription("Port code")
	@NotNull
	@Column
	public String portCode;
	/**
	 * Original Port code (Required)
	 * 
	 */
	@JsonProperty("oport_code")
	@JsonPropertyDescription("Original Port code")
	@NotNull
	@Column
	public String oportCode;
	private final static long serialVersionUID = 6448551263879439729L;

}
