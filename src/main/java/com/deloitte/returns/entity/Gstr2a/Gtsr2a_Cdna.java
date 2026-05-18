
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cfs", "ctin", "cfs3b", "dtcancel", "fldtr1", "flprdr1", "nt" })

@Entity
@Table(name = "cdna", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_Cdna implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Counter party filing status (Required)
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("Counter party filing status")
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
	@JoinColumn(name = "cdna_id")
	public List<Gtsr2a_Nt> nt = new ArrayList<Gtsr2a_Nt>();

	@ManyToOne
	@JoinColumn(name = "gstr2a_id")
	private Gstr2a gstr2a;

	private final static long serialVersionUID = 4688287011274270237L;

}
