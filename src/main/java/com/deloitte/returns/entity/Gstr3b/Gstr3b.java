
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstin", "ret_period", "fil_dt", "qn", "sup_details", "inter_sup", "eco_dtls", "itc_elg",
		"inward_sup", "tx_pmt", "intr_ltfee" })

@Entity
@Data
@Table(name = "gstr3b", schema = "gstr3b")
public class Gstr3b implements Serializable {

	//
	@Column(name = "return_file_count_primary_id")
	private Long returnFileCountPrimaryId;

	@Column(name = "return_file_detail_primary_id")
	private Long returnFileDetailPrimaryId;
//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Supplier GSTIN
	 * 
	 */
	@JsonProperty("gstin")
	@JsonPropertyDescription("Supplier GSTIN")
	@Column
	private String gstin;
	/**
	 * Return period
	 * 
	 */
	@JsonProperty("ret_period")
	@JsonPropertyDescription("Return period")
	@Column
	private String retPeriod;

	@JsonProperty("fil_dt")
	@Column
	private String filDt;
	/**
	 * Questionnaire
	 * 
	 */
	@JsonProperty("qn")
	@JsonPropertyDescription("Questionnaire")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "qn_id")
	private Gstr3b_Qn qn;

	@JsonProperty("sup_details")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "sup_details_id")
	private Gstr3b_SupDetails supDetails;

	@JsonProperty("inter_sup")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "inter_sup_id")
	private Gstr3b_InterSup interSup;

	@JsonProperty("eco_dtls")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "eco_dtls_id")
	private Gstr3b_EcoDtls ecoDtls;

	@JsonProperty("itc_elg")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "itc_elg_id")
	private Gstr3b_ItcElg itcElg;

	@JsonProperty("inward_sup")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "inward_sup_id")
	private Gstr3b_InwardSup inwardSup;

	@JsonProperty("tx_pmt")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "tx_pmt_id")
	private Gstr3b_TxPmt txPmt;
	/**
	 * Interest and LateFee
	 *
	 */
	@JsonProperty("intr_ltfee")
	@JsonPropertyDescription("Interest and LateFee")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "intr_ltfee_id")
	private Gstr3b_IntrLtfee intrLtfee;

	@JsonProperty("liab_breakup")
	@JsonPropertyDescription("Liability and Breakup")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr3b_id")
	private List<Gstr3b_LiabBreakup> liabBreakups;

	@JsonProperty("systemInterest")
	@JsonPropertyDescription("System Interest")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "systemInterest_id")
	private Gstr3b_SystemInterest systemInterest;

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

	private final static long serialVersionUID = 396320474968566029L;

}
