
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
@JsonPropertyOrder({ "refdt", "portcd", "benum", "bedt", "txval", "gstr9Iamt", "gstr9Csamt", "amd" })

@Entity
@Table(name = "impg", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Impg implements Serializable {
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

	@ManyToOne
	@JoinColumn(name = "gstr2a_id")
	private Gstr2a gstr2a;

	private final static long serialVersionUID = -8896976509484103967L;

}
