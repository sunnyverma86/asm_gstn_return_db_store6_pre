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
@Table(name = "inv", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Inv {
	
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

	@JsonProperty("inv_typ")
	private String invTyp;

	@JsonProperty("oidt")
	private String oidt;

	@JsonProperty("oinum")
	private String oinum;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rchrg")
	private String rchrg;

	@JsonProperty("val")
	private double val;
	
	@JsonProperty("irn")
	private String irn;
	
	@JsonProperty("irngendate")
	 private String irngendate;
	
	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_id")
	private List<En_Gstr2A_Itms> itms;
	
	
}