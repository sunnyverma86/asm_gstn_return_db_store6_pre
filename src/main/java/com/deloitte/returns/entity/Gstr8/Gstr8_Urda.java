
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
@JsonPropertyOrder({ "ofp", "eid", "oeid", "grsval", "supret", "amt", "eidname", "oeidname", "chksum" })

@Entity
@Table(name = "urda", schema = "gstr8")
@Data
public class Gstr8_Urda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ofp")
	@JsonPropertyDescription("Original return Period")
	@Column
	public String ofp;

	@JsonProperty("eid")
	@Column
	public String eid;

	@JsonProperty("oeid")
	@Column
	public String oeid;

	@JsonProperty("grsval")
	@Column
	public Double grsval;

	@JsonProperty("supret")
	@Column
	public Double supret;

	@JsonProperty("amt")
	@Column
	public Double amt;

	@JsonProperty("eidname")
	@Column
	public String eidname;

	@JsonProperty("oeidname")
	@Column
	public String oeidname;

	@JsonProperty("chksum")
	@Column
	public String chksum;

	private final static long serialVersionUID = -6908959565117661237L;

}
