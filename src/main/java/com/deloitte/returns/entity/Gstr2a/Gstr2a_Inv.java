
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "chksum", "inum", "idt", "val", "srctyp", "irn", "irngendate", "pos", "rchrg", "inv_typ", "aspd",
		"atyp", "itms" })

@Entity
@Table(name = "inv", schema = "gstr2a")
@Data
public class Gstr2a_Inv implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@Column
	public String chksum;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("inum")
	@Column
	public String inum;

	@JsonProperty("oinum")
	@Column
	public String oinum;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("idt")
	@Column
	public String idt;

	@JsonProperty("oidt")
	@Column
	public String oidt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("val")
	@Column
	public Double val;
	@JsonProperty("srctyp")
	@Column
	public String srctyp;
	@JsonProperty("irn")
	@Column
	public String irn;
	@JsonProperty("irngendate")
	@Column
	public String irngendate;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("pos")
	@Column
	public String pos;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("rchrg")
	@Column
	public Boolean rchrg;
	/**
	 * flag to determine if it is sez or deemed (Required)
	 * 
	 */
	@JsonProperty("inv_typ")
	@JsonPropertyDescription("flag to determine if it is sez or deemed")
	@Column
	public String invTyp;
	/**
	 * Return period in which invoice is amended
	 * 
	 */
	@JsonProperty("aspd")
	@JsonPropertyDescription("Return period in which invoice is amended")
	@Column
	public String aspd;
	/**
	 * flag to determine type of amendment
	 * 
	 */
	@JsonProperty("atyp")
	@JsonPropertyDescription("flag to determine type of amendment")
	@Column
	public String atyp;

	@JsonProperty("diff_percent")
	@JsonPropertyDescription("flag to determine type of amendment")
	@Column
	public Double diffPercent;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_id")
	public List<Gstr2a_Itm> gstr2aItms = new ArrayList<Gstr2a_Itm>();

	public void setRchrg(String rchrg) {
		this.rchrg = Boolean.valueOf(rchrg);
	}

	private final static long serialVersionUID = -2040207201208749713L;

	@JsonProperty("updby")
	private String updby;

	@JsonProperty("cflag")
	private String cflag;

}
