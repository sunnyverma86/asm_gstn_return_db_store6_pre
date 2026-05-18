
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
@JsonPropertyOrder({ "txpay", "pdcashes", "pdcr" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "tx_pb_a", schema = "gstr5")
@Data
public class Gstr5_TxPbA implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("txpay")

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "txpay_id")
	public Gstr5_Txpay gstr5Txpay;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("pdcashes")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pb_a_id")
	public List<Gstr5_TxPbAPdcash> pdcash = new ArrayList<Gstr5_TxPbAPdcash>();
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("pdcr")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pb_a_id")
	public List<Gstr5_Pdcr> gstr5Pdcr = new ArrayList<Gstr5_Pdcr>();

	private final static long serialVersionUID = 1837647095104987009L;

}
