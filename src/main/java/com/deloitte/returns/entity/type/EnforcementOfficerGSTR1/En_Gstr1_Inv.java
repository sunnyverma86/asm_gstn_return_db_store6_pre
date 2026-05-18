package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "inv", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Inv {
	
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("inum")//
    private String inum;

    @JsonProperty("irn")//
    private String irn;
//
    @JsonProperty("irngendate")
    private String irngendate;
//
    @JsonProperty("srctyp")
    private String srctyp;

    @JsonProperty("idt")//
    private String idt;

//    @JsonProperty("oidt")//
//    private String oidt;
//
//    @JsonProperty("nt_dt")
//    private String ntDt;
//
//    @JsonProperty("ont_dt")
//    private String ontDt;
//
//    @JsonProperty("sbnum")//
//    private String sbnum;
//
//    @JsonProperty("sbdt")//
//    private String sbdt;

//    @JsonProperty("sbpcode")//
//    private String sbpcode;

    @JsonProperty("val")//
    private Double val;

//    @JsonProperty("txval")
//    private Double txval;

//    @JsonProperty("rt")
//    private Double rt;
//
//    @JsonProperty("iamt")
//    private Double iamt;
//
//    @JsonProperty("camt")
//    private Double camt;
//
//    @JsonProperty("samt")
//    private Double samt;
//
//    @JsonProperty("csamt")
//    private Double csamt;

    @JsonProperty("pos")//
    private String pos;

 //   @JsonProperty("typ")
//    private String typ;

    @JsonProperty("inv_typ")//
    private String invTyp;

//    @JsonProperty("p_gst")
//    private String pGst;
//
//    @JsonProperty("cfs")
//    private String cfs;

    @JsonProperty("cflag")//
    private String cflag;

    @JsonProperty("rchrg")//
    private String rchrg;

    @JsonProperty("updby")//
    private String updby;

//    @JsonProperty("opd")//
//    private String opd;
//
//    @JsonProperty("etin")//
//    private String etin;

    @JsonProperty("flag")//
    private String flag;

//    @JsonProperty("diff_percent")//
//    private Double diff_percent;

    @JsonProperty("chksum")//
    private String chksum;

//    @JsonProperty("ntty")
//    private String ntty;

    @JsonProperty("itms")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inv_id")
    private List<En_Gstr1_Items> itms;
	
	
	
	
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@JsonProperty("val")
//	private double val;
//	
//	@JsonProperty("diff_percent")
//	private double diff_percent;
//
//	@JsonProperty("oinum")
//	private String oinum;
//
//	@JsonProperty("oidt")
//	private String oidt;
//	
//    @JsonProperty("nt_num")
//    private String ntNum;
//
//    @JsonProperty("ont_num")
//    private String ontNum;
//    
//    @JsonProperty("nt_dt")
//    private String ntDt;
//
//    @JsonProperty("ont_dt")
//    private String ontDt;
//    
//    @JsonProperty("sbnum")
//    private String sbnum;
//
//    @JsonProperty("sbdt")
//    private String sbdt;
//
//    @JsonProperty("sbpcode")
//    private String sbpcode;
//
//
//
//	
//
//	@JsonProperty("updby")
//	private String updby;
//
////	@JsonProperty("irngendate")
////	private String irngendate;
//
//	@JsonProperty("inum")
//	private String inum;
//
//	@JsonProperty("cflag")
//	private String cflag;
//	
//	@JsonProperty("etin")
//	private String etin;
//
//	@JsonProperty("inv_typ")
//	private String invTyp;
//
//	@JsonProperty("pos")
//	private String pos;
//
////	@JsonProperty("srctyp")
////	private String srctyp;
//
//	@JsonProperty("idt")
//	private String idt;
//	
//	@JsonProperty("opd")
//	private String opd;
//
//	@JsonProperty("rchrg")
//	private String rchrg;
//
//	@JsonProperty("chksum")
//	private String chksum;
//
//	@JsonProperty("itms")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "inv_id")
//	private List<En_Gstr1_Items> itms;

}
