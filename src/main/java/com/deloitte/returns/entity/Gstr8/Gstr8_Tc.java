
package com.deloitte.returns.entity.Gstr8;

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
@JsonPropertyOrder({ "stin", "stin_name", "supR", "retsupR", "supU", "retsupU", "amt", "gstr9Camt", "gstr9Samt",
		"gstr9Iamt", "chksum", "pos" })//add pos

@Entity
@Table(name = "tc", schema = "gstr8")
@Data
public class Gstr8_Tc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("stin")
	@Column
	public String stin;
	
	
	@JsonProperty("stin_name")
	@Column
	public String stinName;

	@JsonProperty("supR")
	@Column
	public Double supR;

	@JsonProperty("retsupR")
	@Column
	public Double retsupR;

	@JsonProperty("supU")
	@Column
	public Double supU;

	@JsonProperty("retsupU")
	@Column
	public Double retsupU;

	@JsonProperty("amt")
	@Column
	public Double amt;

	@JsonProperty("camt")
	@Column
	public Double camt;

	@JsonProperty("samt")
	@Column
	public Double samt;

	@JsonProperty("iamt")
	@Column
	public Double iamt;

	@JsonProperty("chksum")
	@Column
	public String chksum;
	
	
	@JsonProperty("flag")
	public String flag;
	
	///add pos
	
		 @JsonProperty("pos")
		 @Column
		 public String pos;
		 
			////////////


	private final static long serialVersionUID = 7099122956634015563L;

}
