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
@Table(name = "nt", schema = "gstr2b")
@Entity
public class Gstr2b_Nt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("diffprcnt")
	private Double diffprcnt;

	@JsonProperty("ntnum")
	private String ntnum;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("suptyp")
	private String suptyp;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("itcavl")
	private String itcavl;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	private List<Gstr2b_Items> items;

	@JsonProperty("igst")
	private long igst;

	@JsonProperty("cgst")
	private double cgst;

	@JsonProperty("sgst")
	private double sgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("txval")
	private double txval;

	@JsonProperty("imsStatus")
	private String imsStatus;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("irn")
	private String irn;

	@JsonProperty("irngendate")
	private String irngendate;

	@JsonProperty("oinum")
	private String oinum;

	@JsonProperty("oidt")
	private String oidt;

	@JsonProperty("ontdt")
	private String ontdt;

	@JsonProperty("ontnum")
	private String ontnum;

	@JsonProperty("onttyp")
	private String onttyp;
	
	@JsonProperty("itcRedReq") //new add
	private String itcRedReq;

	@JsonProperty("remarks") //new add
	private String remarks;


}
