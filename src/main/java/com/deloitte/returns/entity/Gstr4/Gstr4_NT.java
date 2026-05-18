package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "nt", schema = "gstr4")
@Entity
public class Gstr4_NT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("nt_dt")
	private String ntDt;

	@JsonProperty("nt_num")
	private String ntNum;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rchrg")
	private String rchrg;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("val")
	private double val;

	
	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	private List<Gstr4_Itms> itms;
	
	@JsonProperty("flag")
	private String flag;
	
	@JsonProperty("chksum")
	private String chksum;
	

}
