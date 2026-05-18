
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tx_pb_a", "tx_pb_b" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "txpd", schema = "gstr5")
@Data
public class Gstr5_Txpd implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("tx_pb_a")

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pb_a_id")
	public Gstr5_TxPbA gstr5TxPbA;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("tx_pb_b")

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pb_b_id")
	public Gstr5_TxPbB gstr5TxPbB;

	private final static long serialVersionUID = -6580008446281621007L;

}
