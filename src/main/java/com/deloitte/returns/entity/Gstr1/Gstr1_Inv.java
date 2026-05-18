package com.deloitte.returns.entity.Gstr1;

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
@Table(name = "inv", schema = "gstr1")
@Entity
public class Gstr1_Inv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("irngendate")
	private String irngendate;

	@JsonProperty("irn")
	private String irn;

	@JsonProperty("cflag")
	private String cflag;

	@JsonProperty("rchrg")
	private String rchrg;

	@JsonProperty("updby")
	private String updby;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("diff_percent")
	private Double diffPercent;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("expt_amt")
	private Double exptAmt;

	@JsonProperty("ngsup_amt")
	private Double ngsupAmt;

	@JsonProperty("nil_amt")
	private Double nilAmt;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("oidt")
	private String oidt;

	@JsonProperty("oinum")
	private String oinum;

	@JsonProperty("sbdt")
	private String sbdt;

	@JsonProperty("sbnum")
	private String sbnum;

	@JsonProperty("sbpcode")
	private String sbpcode;

	@JsonProperty("inv_typ")
	private String invTyp;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("opd")
	private String opd;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_id")
	private List<Gstr1_Itms> itms;

}
