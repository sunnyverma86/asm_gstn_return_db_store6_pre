package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rplyextnnotice", schema = "refund")
public class Refund_Rplyextnnotice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcRefid")
	private String ntcRefid;

	@JsonProperty("replydt")
	private String replydt;

	@JsonProperty("ntcDate")
	private String ntcDate;

	@JsonProperty("replyId")
	private String replyID;

	@JsonProperty("action")
	private String action;
	
	@JsonProperty("noticeType")
	private String noticeType;

	@JsonProperty("prsnlhrng")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prsnlhrng_id")
	private Refund_Prsnlhrng prsnlhrng;

	@JsonProperty("rplyextnreq")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rplyextnreq_id")
	private Refund_Rplyextnreq rplyextnreq;



}
