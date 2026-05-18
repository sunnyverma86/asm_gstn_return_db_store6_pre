package com.deloitte.returns.entity.Refund;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sdtls", schema = "refund")
public class Refund_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("duedate")
	private String duedate;

	@JsonProperty("rsnnotice")
	private String rsnnotice;

	@JsonProperty("rsnnoticeothers")
	private String rsnnoticeothers;

	@JsonProperty("tynotice")
	private String tynotice;

	@JsonProperty("pmtordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pmtordervo_id")
	private Refund_Pmtordervo pmtordervo;

	@JsonProperty("comordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comordervo_id")
	private Refund_Comordervo comordervo;

	@JsonProperty("sancordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancordervo_id")
	private Refund_Sancordervo sancordervo;

	@JsonProperty("withheldordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "withheldordervo_id")
	private Refund_Withheldordervo withheldordervo;

	@JsonProperty("proordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "proordervo_id")
	private Refund_Proordervo proordervo;

	@JsonProperty("payadviceordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payadviceordervo_id")
	private Refund_Payadviceordervo payadviceordervo;

	@JsonProperty("pmtsancordervo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pmtsancordervo_id")
	private Refund_Pmtsancordervo pmtsancordervo;

	@JsonProperty("scninadmrsnList")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private List<Refund_ScninadmrsnList> scninadmrsnList;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("defrsnnoticeList")
	private List<String> defrsnnoticeList;

	@JsonProperty("remarks")
	@Column(length = 500)
	private String remarks;

	@JsonProperty("rplyextnnotice")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rplyextnnotice_id")
	private Refund_Rplyextnnotice rplyextnnotice;

}