
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
@JsonPropertyOrder({ "ctin", "cfs", "cfs3b", "dtcancel", "fldtr1", "flprdr1", "inv" })

@Entity
@Table(name = "b2b", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_B2b implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("ctin")
	@Column
	public String ctin;
	/**
	 * R1_Fil_Status (Required)
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("R1_Fil_Status")
	@Column
	public String cfs;
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
	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	public List<Gtsr2a_Inv> inv = new ArrayList<Gtsr2a_Inv>();

	@ManyToOne
	@JoinColumn(name = "gstr2a_id")
	private Gstr2a gstr2a;

	private final static long serialVersionUID = -2615047501174436180L;

}
