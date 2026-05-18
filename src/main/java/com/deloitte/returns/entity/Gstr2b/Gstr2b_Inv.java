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
@Table(name = "inv", schema = "gstr2b")
@Entity
public class Gstr2b_Inv {

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
	private double val;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("itcavl")
	private String itcavl;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("diffprcnt")
	private long diffprcnt;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_id")
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
