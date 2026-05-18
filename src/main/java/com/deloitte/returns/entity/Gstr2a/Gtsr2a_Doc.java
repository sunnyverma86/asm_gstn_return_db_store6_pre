
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstr9Camt", "chksum", "aspd", "atyp", "gstr9Csamt", "odocdt", "odocnum", "docdt", "docnum",
		"gstr9Iamt", "isd_docty", "itc_elg", "gstr9Samt" })

@Entity
@Table(name = "doc", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_Doc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("Camt")
	@Column
	public Double camt;
	@JsonProperty("chksum")
	@Column
	public String chksum;
	/**
	 * Original period in which invoice was added
	 * 
	 */
	@JsonProperty("aspd")
	@JsonPropertyDescription("Original period in which invoice was added")
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
	@JsonProperty("Csamt")
	@Column
	public Double csamt;
	@JsonProperty("cess")
	@Column
	public Double cess;
	@JsonProperty("odocdt")
	@Column
	public String odocdt;
	/**
	 * Revised Document number
	 * 
	 */
	@JsonProperty("odocnum")
	@JsonPropertyDescription(" Revised Document number")
	@Column
	public String odocnum;
	@JsonProperty("docdt")
	@Column
	public String docdt;
	/**
	 * Document number
	 * 
	 */
	@JsonProperty("docnum")
	@JsonPropertyDescription("Document number")
	@Column
	public String docnum;
	@JsonProperty("Iamt")
	@Column
	public Double iamt;
	/**
	 * document type
	 * 
	 */
	@JsonProperty("isd_docty")
	@JsonPropertyDescription("document type")
	@Column
	public String isdDocty;
	/**
	 * Eligible for ITC
	 * 
	 */
	@JsonProperty("itc_elg")
	@JsonPropertyDescription("Eligible for ITC")
	@Column
	public String itcElg;
	@JsonProperty("Samt")
	@Column
	public Double samt;

	@ManyToOne
	@JoinColumn(name = "isda_id")
	private Gtsr2a_Isda isda;

	@ManyToOne
	@JoinColumn(name = "isd_id")
	private Gtsr2a_Isd isd;

	private final static long serialVersionUID = 5392851843939293476L;

}
