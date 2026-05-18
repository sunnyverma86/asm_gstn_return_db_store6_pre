package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "orddtl", schema = "appeal_by_tax_payer")

public class Appeal_Orddtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordtyp")
	private String ordtyp;

	@JsonProperty("ordnum")
	private String ordnum;

	@JsonProperty("orddt")
	private String orddt;

	@JsonProperty("dmdid")
	private String dmdid;

	@JsonProperty("ordcommdt")
	private String ordcommdt;

	@JsonProperty("ordofficer")
	private String ordofficer;
	
	@JsonProperty("prn")
	private String prn;

	@JsonProperty("predepotax")
	private String predepotax;

	@JsonProperty("addr")
	private String addr;
	
	@JsonProperty("enfdisamtflag")
	private Boolean enfdisamtflag;
	
	@JsonProperty("prdofdisp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prdofdisp_id")
	private Appeal_Prdofdisp prdofdisp;

	@JsonProperty("initamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "initamt_id")
	private Appeal_Initamt initamt;

	@JsonProperty("paidamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paidamt_id")
	private Appeal_Paidamt paidamt;

	@JsonProperty("dispamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dispamt_id")
	private Appeal_Dispamt dispamt;
	
	@JsonProperty("payableamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payableamt_id")
	private Appeal_Payableamt payableamt;

	@JsonProperty("predepositpenality")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "predepositpenality_id")
	private Appeal_Predepositpenality predepositpenality;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Appeal_Todtls todtls;
	
	@JsonProperty("disputeDetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private List<Appeal_DisputeDetails> disputeDetails;

	@JsonProperty("demandCreatedDetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private List<Appeal_DemandCreatedDetails> demandCreatedDetails;

	@JsonProperty("demandAdmittedDetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private List<Appeal_DemandAdmittedDetails> demandAdmittedDetails;

	@JsonProperty("mfydmdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private List<Appeal_Mfydmdtls> mfydmdtls;





}
