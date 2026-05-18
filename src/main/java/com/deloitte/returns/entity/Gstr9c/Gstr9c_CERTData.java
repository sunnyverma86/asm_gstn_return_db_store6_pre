package com.deloitte.returns.entity.Gstr9c;

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
@Table(name = "cert_data", schema = "gstr9c")
public class Gstr9c_CERTData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bal_sheet_date")
	private String balSheetDate;

	@JsonProperty("beg_date")
	private String begDate;

	@JsonProperty("cash_from_date")
	private String cashFromDate;

	@JsonProperty("cash_to_date")
	private String cashToDate;

	@JsonProperty("date")
	private String date;

	@JsonProperty("end_date")
	private String endDateData;

	@JsonProperty("mem_no")
	private String memNo;

	@JsonProperty("place")
	private String place;

	@JsonProperty("signatory")
	private String signatory;

	@JsonProperty("taxpayer_name")
	private String taxpayerName;

	@JsonProperty("pronoun")
	private String pronoun;

	@JsonProperty("doc_stat")
	private String docStat;

	@JsonProperty("acc_typ")
	private String accTyp;

	@JsonProperty("addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private Gstr9c_Addr addr;

	@JsonProperty("audit_addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "audit_addr_id")
	private Gstr9c_AuditAddr auditAddr;

	@JsonProperty("cert_textpartb1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_textpartb1_id")
	private Gstr9c_CERTTextpartb1 certTextpartb1;

	@JsonProperty("cert_textpartb2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_textpartb2_id")
	private Gstr9c_CERTTextpartb2 certTextpartb2;

}
