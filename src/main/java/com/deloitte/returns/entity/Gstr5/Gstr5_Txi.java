
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "diffItc", "liab" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "txi", schema = "gstr5")
@Data
public class Gstr5_Txi implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("diffItc")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "txi_id")
	public List<Gstr5_DiffItc> diffItc = new ArrayList<Gstr5_DiffItc>();
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("liab")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "txi_id")
	public List<Gstr5_Liab> gstr5Liab = new ArrayList<Gstr5_Liab>();
	private final static long serialVersionUID = 3941459352527406792L;

}
