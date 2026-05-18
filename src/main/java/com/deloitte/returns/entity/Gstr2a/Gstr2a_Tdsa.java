
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
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ogstin_ded", "omonth", "oamt_ded", "gstin_ded", "amt_ded", "iamt", "camt", "samt" })

@Entity
@Table(name = "tdsa", schema = "gstr2a")
@Data
public class Gstr2a_Tdsa implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ogstin_ded")
	@Column
	public String ogstinDed;
	/**
	 * Orginal period of the TDS
	 * 
	 */
	@JsonProperty("omonth")
	@JsonPropertyDescription("Orginal period of the TDS")
	@Column
	public String omonth;
	@JsonProperty("oamt_ded")
	@Column
	public Double oamtDed;
	@JsonProperty("gstin_ded")
	@Column
	public String gstinDed;
	@JsonProperty("amt_ded")
	@Column
	public Double amtDed;
	@JsonProperty("iamt")
	@Column
	public Double iamt;
	@JsonProperty("camt")
	@Column
	public Double camt;
	@JsonProperty("samt")
	@Column
	public Double samt;

	private final static long serialVersionUID = -1061733572688487539L;

}
