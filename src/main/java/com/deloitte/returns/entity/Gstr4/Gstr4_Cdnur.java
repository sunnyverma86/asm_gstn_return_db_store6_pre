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
@Table(name = "cdnur", schema = "gstr4")
@Entity
public class Gstr4_Cdnur {

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

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("val")
	private double val;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnur_id")
	private List<Gstr4_Itms> itms;

}
