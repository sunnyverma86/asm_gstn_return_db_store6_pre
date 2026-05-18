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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cdnur", schema = "gstr1a")
public class Gstr1A_Cdnur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("irn")
	private String irn;

	@JsonProperty("srctyp")
	private String srctyp;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("irngendate")
	private String irngendate;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("diff_percent")
	private String diff_percent;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnur_id")
	private List<Gstr1A_Itms> itms;

	@JsonProperty("val")
	private double val;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("d_flag")
	private String dFlag;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("nt_num")
	private String ntNum;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("nt_dt")
	private String ntDt;

	@JsonProperty("chksum")
	private String chksum;

	private Double doubleValue;
	private Long integerValue;
	private Boolean boolValue;

	@Transient
	private Object[] anythingArrayValue;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnur_class_id")
	private Gstr1A_CdnurClass cdnurClassValue;

	@JsonProperty("pos")
	private String pos;

}