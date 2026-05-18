package com.deloitte.returns.entity.Gstr2b;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "b2b", schema = "gstr2b")
@Entity
public class Gstr2b_B2B {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("num")
	private double num;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("supprd")
	private String supprd;

	@JsonProperty("supfildt")
	private String supfildt;

	@JsonProperty("txval")
	private double txval;

	@JsonProperty("ttldocs")
	private double ttldocs;

	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private List<Gstr2b_Inv> inv;

}
