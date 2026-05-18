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
@Table(name = "cdnur_class", schema = "gstr1")
public class Gstr1_CdnurClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("d_flag")
	private String dFlag;

	@JsonProperty("diff_percent")
	private Double diffPercent;

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

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnur_class_id")
	private List<Gstr1_Itms> itms;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("chksum")
	@Column(length = 2000)
	private String chksum;

}
