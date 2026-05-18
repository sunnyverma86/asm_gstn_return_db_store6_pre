package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enforcement_officer_gstr3b", schema = "enforcement_officer_gstr3b")
public class EnforcementOfficerGSTR3B {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin")
    private String gstin;
	
	@JsonProperty("ret_period")
    private String retPeriod;

	@JsonProperty("sup_details")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sup_details_id")
	public En_Gstr3B_SupDetails supDetails;

	@JsonProperty("inter_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inter_sup_id")
	public En_Gstr3B_InterSup interSup;

	@JsonProperty("itc_elg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	public En_Gstr3B_ItcElg itcElg;

	@JsonProperty("inward_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inward_sup_id")
	public En_Gstr3B_InwardSup inwardSup;

	@JsonProperty("tx_pmt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pmt_id")
	public En_Gstr3B_TxPmt txPmt;

	@JsonProperty("intr_ltfee")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_ltfee_id")
	public En_Gstr3B_IntrLtfee intrLtfee;

	@JsonProperty("eco_dtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eco_dtls_id")
	public En_Gstr3B_EcoDtls ecoDtls;

}