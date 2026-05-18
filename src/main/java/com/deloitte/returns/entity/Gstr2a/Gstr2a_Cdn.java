
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
@JsonPropertyOrder({ "cfs", "ctin", "cfs3b", "dtcancel", "fldtr1", "flprdr1", "nt" })

@Entity
@Table(name = "cdn", schema = "gstr2a")
@Data
public class Gstr2a_Cdn implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * R1_Fil_Status (Required)
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("R1_Fil_Status")
	@Column
	public String cfs;
	/**
	 * Counter party gstin (Required)
	 * 
	 */
	@JsonProperty("ctin")
	@JsonPropertyDescription("Counter party gstin")
	@Column
	public String ctin;
	/**
	 * R3B_Fil_Status
	 * 
	 */
	@JsonProperty("cfs3b")
	@JsonPropertyDescription("R3B_Fil_Status")
	@Column
	public String cfs3b;
	/**
	 * Date of cancellation of supplier
	 * 
	 */
	@JsonProperty("dtcancel")
	@JsonPropertyDescription("Date of cancellation of supplier")
	@Column
	public String dtcancel;
	/**
	 * Date of filing GSTR1/5 of supplier
	 * 
	 */
	@JsonProperty("fldtr1")
	@JsonPropertyDescription("Date of filing GSTR1/5 of supplier")
	@Column
	public String fldtr1;
	/**
	 * Filing period of GSTR1/5 of supplier
	 * 
	 */
	@JsonProperty("flprdr1")
	@JsonPropertyDescription("Filing period of GSTR1/5 of supplier")
	@Column
	public String flprdr1;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("nt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdn_id")
	public List<Gstr2a_Nt> gstr2aNt = new ArrayList<Gstr2a_Nt>();

	private final static long serialVersionUID = -7570259261761612210L;

}
