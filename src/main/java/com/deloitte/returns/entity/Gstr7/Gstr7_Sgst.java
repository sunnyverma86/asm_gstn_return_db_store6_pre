package com.deloitte.returns.entity.Gstr7;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sgst",schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tx", "intr", "pen", "fee", "oth", "tot" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_Sgst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tx")
	@Column(name = "tx")
	public Double tx;

	@JsonProperty("intr")
	@Column(name = "intr")
	public Double intr;

	@JsonProperty("pen")
	@Column(name = "pen")
	public Double pen;

	@JsonProperty("fee")
	@Column(name = "fee")
	public Double fee;

	@JsonProperty("oth")
	@Column(name = "oth")
	public Double oth;

	@JsonProperty("tot")
	@Column(name = "tot")
	public Double tot;

}
