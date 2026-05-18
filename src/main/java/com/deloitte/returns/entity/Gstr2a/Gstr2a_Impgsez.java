
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "refdt", "portcd", "benum", "bedt", "sgstin", "tdname", "txval", "iamt", "csamt", "amd" })

@Entity
@Table(name = "impgsez", schema = "gstr2a")
@Data
public class Gstr2a_Impgsez implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("refdt")
	@Column
	public String refdt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("portcd")
	@Column
	public String portcd;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("benum")
	@Column
	public Double benum;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("bedt")
	@Column
	public String bedt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("sgstin")
	@Column
	public String sgstin;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("tdname")
	@Column
	public String tdname;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("txval")
	@Column
	public Double txval;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("Iamt")
	@Column
	public Double iamt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("Csamt")
	@Column
	public Double csamt;
	@JsonProperty("amd")
	@Column
	public String amd;

	private final static long serialVersionUID = 3984060016191725026L;

}
