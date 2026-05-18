
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
@JsonPropertyOrder({ "eid", "grsval", "supret", "amt", "eidname", "chksum" })

@Entity
@Table(name = "urd", schema = "gstr8")
@Data
public class Gstr8_Urd implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eid")
	@Column
	public String eid;

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

	@JsonProperty("chksum")
	@Column
	public String chksum;

	private final static long serialVersionUID = -6153498309691391698L;

}
