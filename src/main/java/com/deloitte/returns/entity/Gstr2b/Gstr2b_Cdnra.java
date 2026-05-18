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
@Table(name = "cdnra", schema = "gstr2b")
@Entity
public class Gstr2b_Cdnra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("diffprcnt")
	private Double diffprcnt;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("itcavl")
	private String itcavl;

	@JsonProperty("ntnum")
	private String ntnum;

	@JsonProperty("ontdt")
	private String ontdt;

	@JsonProperty("ontnum")
	private String ontnum;

	@JsonProperty("onttyp")
	private String onttyp;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("suptyp")
	private String suptyp;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("val")
	private double val;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("nttyp")
	private String nttyp;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("supfildt")
	private String supfildt;

	@JsonProperty("supprd")
	private String supprd;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("ttldocs")
	private double ttldocs;

	@JsonProperty("txval")
	private double txval;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnra_id")
	private List<Gstr2b_Items> items;
	
	@JsonProperty("nt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnra_id")
	private List<Gstr2b_Nt> nt;
	
	@JsonProperty("num")
	private double num;

	@JsonProperty("rt")
	private double rt;

}