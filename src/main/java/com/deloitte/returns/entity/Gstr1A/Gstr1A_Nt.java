package com.deloitte.returns.entity.Gstr1A;

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
@Table(name = "nt", schema = "gstr1a")
public class Gstr1A_Nt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("d_flag")
	private String dFlag;

	@JsonProperty("updby")
	private String updby; // added

	@JsonProperty("cflag")
	private String cflag; // added

	@JsonProperty("diff_percent")
	private Double diffPercent;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("irn")
	private String irn;

	@JsonProperty("irngendate")
	private String irngendate;

	@JsonProperty("nt_dt")
	private String ntDt;

	@JsonProperty("nt_num")
	private String ntNum;

	@JsonProperty("ont_dt")
	private String ontDt;

	@JsonProperty("ont_num")
	private String ontNum;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("inv_typ")
	private String invTyp;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nt_id")
	private List<Gstr1A_Itms> itms;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("rchrg")
	private String rchrg;

}
