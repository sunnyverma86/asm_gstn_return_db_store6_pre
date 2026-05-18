
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
@JsonPropertyOrder({ "gstin_ded", "amt_ded", "iamt", "camt", "samt" })

@Entity
@Table(name = "td", schema = "gstr2a")
@Data
public class Gstr2a_Td implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	private final static long serialVersionUID = -4754014155468265851L;

}
