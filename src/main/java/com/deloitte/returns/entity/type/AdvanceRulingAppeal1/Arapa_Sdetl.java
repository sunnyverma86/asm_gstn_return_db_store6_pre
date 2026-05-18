package com.deloitte.returns.entity.type.AdvanceRulingAppeal1;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sdetl", schema = "advance_ruling_appeal")

public class Arapa_Sdetl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aplcnType")
	private String aplcnType;

	@JsonProperty("noticeType")
	private String noticeType;

	@JsonProperty("noticeNo")
	private String noticeNo;

	@JsonProperty("previousarn")
	private String previousarn;

	@JsonProperty("boEmail")
	private String boEmail;

	@JsonProperty("boMobile")
	private String boMobile;

	@JsonProperty("arOrdNum")
	private String arOrdNum;

	@JsonProperty("arOrdDt")
	private String arOrdDt;
	
	@JsonProperty("toBeHeard")
	private String toBeHeard;

	@JsonProperty("originalCaseId")
	private String originalCaseId;

	@JsonProperty("fileTm")
	private String fileTm;

	@JsonProperty("fileDt")
	private String fileDt;

	@JsonProperty("filePlace")
	private String filePlace;

	@JsonProperty("lgnm")
	private String lgnm;
	
	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("applcntst")
	private String applcntst;

	@JsonProperty("appfiledfor")
	private String appfiledfor;

	@JsonProperty("authname")
	private String authname;

	@JsonProperty("sdwnm")
	private String sdwnm;

	@JsonProperty("desigtp")
	private String desigtp;
	
	@JsonProperty("subject")
	private String subject;

	@JsonProperty("subjectDesc")
	private String subjectDesc;

	@JsonProperty("others")
	private String others;

	@JsonProperty("prevDateOfHearing")
	private String prevDateOfHearing;

	@JsonProperty("prevStatus")
	private String prevStatus;
	
	@JsonProperty("newDateOfHearing")
	private String newDateOfHearing;

	@JsonProperty("timeOfHearing")
	private String timeOfHearing;

	
	@JsonProperty("placeOfHearing")
	private String placeOfHearing;

	@JsonProperty("hr")
	private String hr;
	
	@JsonProperty("sec")
	private String sec;

	@JsonProperty("meridian")
	private String meridian;

	@JsonProperty("issuedOn")
	private String issuedOn;

	@JsonProperty("gstref")
	private String gstref;

	@JsonProperty("issuedBy")
	private String issuedBy;
	
//	@JsonProperty("on")
//	private String on;
	
	@JsonProperty("doo")
	private String doo;

	@JsonProperty("rectno")
	private String rectno;

	@JsonProperty("orderNo")
	private String orderNo;

	@JsonProperty("orderDate")
	private String orderDate;
	
	@JsonProperty("refArn")
	private String refArn;

	@JsonProperty("dateRefArn")
	private String dateRefArn;

	@JsonProperty("linkedOrderNo")
	private String linkedOrderNo;

	@JsonProperty("linkedOrderDt")
	private String linkedOrderDt;
	
	@JsonProperty("voidRef")
	private String voidRef;

    @JsonProperty("appealDelay")
    private Integer appealDelay; 
	
    @JsonProperty("corrad")
    private Boolean corrad;  
    
    @JsonProperty("verify1")
    private Boolean verify1;  
    
    @JsonProperty("verify2")
    private Boolean verify2;  
    
    @JsonProperty("verify4")
    private Boolean verify4;  
    
	@JsonProperty("rsad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rsad_id")
	private Arapa_Rsad rsad;

	@JsonProperty("crad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crad_id")
	private Arapa_Crad crad;

	@JsonProperty("jdtlsst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jdtlsst_id")
	private Arapa_Jdtlsst jdtlsst;
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdetl_id")
	private List<Arapa_Maindocs> maindocs;








}
