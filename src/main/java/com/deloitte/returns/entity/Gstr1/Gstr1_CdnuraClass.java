package com.deloitte.returns.entity.Gstr1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "cdnura_class", schema = "gstr1")
public class Gstr1_CdnuraClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("d_flag")
	private String dFlag;

	@JsonProperty("diff_percent")
	private Double diffPercent;

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

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnura_class_id")
	private List<Gstr1_Itms> itms;

	@JsonProperty("irn")
	@Column(length = 2000)
	private String irn;

	@JsonProperty("srctyp")
	@Column(length = 2000)
	private String srctyp;

	@JsonProperty("irngendate")
	private String irngendate;

}
