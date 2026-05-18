
package com.deloitte.returns.entity.Gstr8;

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
@JsonPropertyOrder({ "ofp", "stin", "ostin", "stin_name", "ostin_name", "supR", "retsupR", "supU", "retsupU", "amt",
		"gstr9Camt", "gstr9Samt", "gstr9Iamt", "source", "chksum", "actn", "addpos", // add
		"opos", // add
		"pos", // add
		"comment", // add
		"remarks"// add
})

@Entity
@Table(name = "tcsa", schema = "gstr8")
@Data
public class Gstr8_Tcsa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ofp")
	@JsonPropertyDescription("Original return Period of TCS")
	@Column
	public String ofp;

	@JsonProperty("stin")
	@Column
	public String stin;

	@JsonProperty("ostin")
	@Column
	public String ostin;

	@JsonProperty("stin_name")
	@Column
	public String stinName;

	@JsonProperty("ostin_name")
	@Column
	public String ostinName;

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

	@JsonProperty("source")
	@Column
	public String source;

	@JsonProperty("chksum")
	@Column
	public String chksum;

	@JsonProperty("actn")
	@Column
	public Boolean actn;

	@JsonProperty("flag")
	public String flag;

	//// add
	@JsonProperty("addpos")
	@Column
	public String addpos;

	@JsonProperty("opos")
	@Column
	public String opos;

	@JsonProperty("pos")
	@Column
	public String pos;

	@JsonProperty("comment")
	@Column
	public String comment;

	@JsonProperty("remarks")
	@Column
	public String remarks;

	/////

	private final static long serialVersionUID = 985360607823682943L;

	public void setActn(String actn) {
		this.actn = Boolean.valueOf(actn);
	}

}
