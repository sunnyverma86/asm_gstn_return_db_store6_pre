
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * details of the items of invoice
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "txval", "gstr9Iamt", "gstr9Camt", "gstr9Samt", "gstr9Csamt", "rt" })

@Entity
@Table(name = "itm_det", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_ItmDet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("txval")
	@Column
	public Double txval;
	@JsonProperty("Iamt")
	@Column
	public Double iamt;
	@JsonProperty("Camt")
	@Column
	public Double camt;
	@JsonProperty("Samt")
	@Column
	public Double samt;
	@JsonProperty("Csamt")
	@Column
	public Double csamt;
	/**
	 * Tax Rate (Required)
	 * 
	 */
	@JsonProperty("rt")
	@JsonPropertyDescription("Tax Rate")
	@Column
	public Double rt;

	private final static long serialVersionUID = -442361204763153678L;

}
