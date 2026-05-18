package com.deloitte.returns.entity.Gstr2b;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doclist", schema = "gstr2b")
@Entity
public class Gstr2b_Doclist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("val")
	private long val;

	@JsonProperty("odoctyp")
	private String odoctyp;

	@JsonProperty("doctyp")
	private String doctyp;

	@JsonProperty("docnum")
	private String docnum;

	@JsonProperty("docdt")
	private String docdt;

	@JsonProperty("igst")
	private double igst;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("txval")
	private long txval;

	@JsonProperty("itcelg")
	private String itcelg;

	@JsonProperty("imsStatus")
	private String imsStatus;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("itcavl")
	private String itcavl;
	
	@JsonProperty("rsn")
	private String rsn;
	
	
	@JsonProperty("oinum")
	private String oinum;
	
	@JsonProperty("oidt")
	private String oidt;
	
	@JsonProperty("oinvnum")
	private String oinvnum;
	
	@JsonProperty("oinvdt")
	private String oinvdt;
	
	@JsonProperty("odocnum")
	private String odocnum;
	
	@JsonProperty("odocdt")
	private String odocdt;
	

}
