
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
@JsonPropertyOrder({ "chksum", "idt", "inum", "val", "srctyp", "irn", "irngendate", "pos", "rchrg", "inv_typ", "dflag",
		"aspd", "atyp", "itms", "nt_dt", "nt_num", "p_gst", "ntty" })

@Entity
@Table(name = "nt", schema = "gstr2a")
@Data
public class Gstr2a_Nt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Checksum (Required)
	 * 
	 */
	@JsonProperty("chksum")
	@JsonPropertyDescription("Checksum")
	@Column
	public String chksum;

	@JsonProperty("diff_percent")
	@JsonPropertyDescription("diff percent")
	@Column
	public Double diffPercent;
	/**
	 * Involice date
	 * 
	 */
	@JsonProperty("idt")
	@JsonPropertyDescription("Involice date")
	@Column
	public String idt;
	/**
	 * Involice number
	 * 
	 */
	@JsonProperty("inum")
	@JsonPropertyDescription("Involice number")
	@Column
	public String inum;
	/**
	 * Note Value (Required)
	 * 
	 */
	@JsonProperty("val")
	@JsonPropertyDescription("Note Value")
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
	 * Maintained in GST System common database POS as provided in law / actual
	 * provision of service.
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("Maintained in GST System common database POS as provided in law / actual provision of service.")
	@Column
	public String pos;
	/**
	 * Reverse Charge
	 * 
	 */
	@JsonProperty("rchrg")
	@JsonPropertyDescription("Reverse Charge")
	@Column
	public String rchrg;

	/**
	 * flag to determine if it is sez or deemed
	 * 
	 */
	@JsonProperty("inv_typ")
	@JsonPropertyDescription("flag to determine if it is sez or deemed")
	@Column
	public String invTyp;
	/**
	 * delink flag
	 * 
	 */
	@JsonProperty("d_flag")
	@JsonPropertyDescription("delink flag")
	@Column
	public String dflag;
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
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	public List<Gstr2a_Itm> gstr2aItms = new ArrayList<Gstr2a_Itm>();
	/**
	 * note date (Required)
	 * 
	 */
	@JsonProperty("nt_dt")
	@JsonPropertyDescription("note date")
	@Column
	public String ntDt;
	/**
	 * note number (Required)
	 * 
	 */
	@JsonProperty("nt_num")
	@JsonPropertyDescription("note number")
	@Column
	public String ntNum;
	/**
	 * Pre GST Regime Dr./ Cr. Notes
	 * 
	 */
	@JsonProperty("p_gst")
	@JsonPropertyDescription("Pre GST Regime Dr./ Cr. Notes")
	@Column
	public String pGst;
	/**
	 * note type (Required)
	 * 
	 */
	@JsonProperty("ntty")
	@JsonPropertyDescription("note type")
	@Column
	public String ntty;

	private final static long serialVersionUID = 8213628240598838337L;

}
