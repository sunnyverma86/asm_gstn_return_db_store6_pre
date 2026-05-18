package com.deloitte.returns.entity.Gstr6;

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
@Table(name = "nt", schema = "gstr6")
@Entity
public class Gstr6_NT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("flag")
	private String flag;

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

	@JsonProperty("opd")
	private String opd;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("updby")
	private String updby;

	@JsonProperty("val")
	private double val;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	private List<Gstr6_Itms> itms;

}