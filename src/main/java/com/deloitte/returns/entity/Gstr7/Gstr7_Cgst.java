package com.deloitte.returns.entity.Gstr7;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tx", "intr", "pen", "fee", "oth", "tot" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "cgst",schema = "gstr7")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_Cgst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tx")
	@Column(name = "tx")
	private Double tx;

	@JsonProperty("intr")
	@Column(name = "intr")
	private Double intr;

	@JsonProperty("pen")
	@Column(name = "pen")
	private Double pen;

	@JsonProperty("fee")
	@Column(name = "fee")
	private Double fee;

	@JsonProperty("oth")
	@Column(name = "oth")
	private Double oth;

	@JsonProperty("tot")
	@Column(name = "tot")
	private Double tot;
}
