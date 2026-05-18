
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "py_det", "pdcashes" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "tx_pb_b", schema = "gstr5")
@Data
public class Gstr5_TxPbB implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("py_det")

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "py_det_id")
	public Gstr5_PyDet gstr5PyDet;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("pdcashes")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pb_b_id")
	public List<Gstr5_Pdcash> gstr5Pdcashes = new ArrayList<Gstr5_Pdcash>();
	private final static long serialVersionUID = 6643451145760673536L;

}
