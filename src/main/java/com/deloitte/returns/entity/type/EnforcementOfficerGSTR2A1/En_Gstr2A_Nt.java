package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

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
@Entity
@Table(name = "nt", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Nt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aspd")
	private String aspd;

	@JsonProperty("atyp")
	private String atyp;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("diff_percent")
	private Double diffPercent;

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

	@JsonProperty("ont_dt")
	private String ontDt;

	@JsonProperty("ont_num")
	private String ontNum;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("val")
	private double val;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	private List<En_Gstr2A_Itms> itms;

}