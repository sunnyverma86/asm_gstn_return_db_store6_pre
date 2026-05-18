package com.deloitte.returns.entity.type.AdvanceRulingReference1;
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
@Table(name = "sdetl", schema = "advance_ruling_reference")

public class Ararf_Sdetl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("noticeType")
	private String noticeType;

	@JsonProperty("noticeNo")
	private String noticeNo;
	
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
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdetl_id")
	private List<Ararf_Maindocs> maindocs;


	


}
