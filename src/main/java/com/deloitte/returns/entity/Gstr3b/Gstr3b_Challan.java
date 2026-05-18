package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ret_period", "pdcash" })

@Entity
@Data
@Table(name = "challan", schema = "gstr3b")
public class Gstr3b_Challan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ret_period")
	@Column
	private String retPeriod;

	@JsonProperty("pdcash")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "pdcash_id")
	private Gstr3b_Pdcash pdcash;

	private final static long serialVersionUID = -1622834901452959097L;

}
