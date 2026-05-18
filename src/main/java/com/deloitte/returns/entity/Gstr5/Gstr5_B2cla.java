
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "inv", "pos" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "b2cla", schema = "gstr5")
@Data
public class Gstr5_B2cla implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("inv")
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2cla_id")
	public List<Gstr5_Inv> gstr5Inv = new ArrayList<Gstr5_Inv>();
	/**
	 * Place of supply (Required)
	 * 
	 */
	@JsonProperty("pos")
	@JsonPropertyDescription("Place of supply")
	@NotNull
	@Column
	public String pos;
	private final static long serialVersionUID = -5741741831415259279L;

}
