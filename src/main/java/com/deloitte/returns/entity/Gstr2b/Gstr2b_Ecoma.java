package com.deloitte.returns.entity.Gstr2b;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "ecoma", schema = "gstr2b")
@Entity
public class Gstr2b_Ecoma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("num")
	private double num;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("diffprcnt")
	private Double diffprcnt;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("irn")
	private String irn;

	@JsonProperty("irngendate")
	private String irngendate;

	@JsonProperty("itcavl")
	private String itcavl;

	@JsonProperty("ntnum")
	private String ntnum;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("suptyp")
	private String suptyp;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("val")
	private double val;

	@JsonProperty("txval")
	private double txval;

	@JsonProperty("igst")
	private double igst;

	@JsonProperty("cgst")
	private double cgst;

	@JsonProperty("sgst")
	private double sgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("supprd")
	private String supprd;

	@JsonProperty("supfildt")
	private String supfildt;

	@JsonProperty("nttyp")
	private String nttyp;

	@JsonProperty("ttldocs")
	private long ttldocs;
	
	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private List<Gstr2b_Inv> inv;

}