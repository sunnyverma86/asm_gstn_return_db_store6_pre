package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
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
@Table(name = "table15", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table15 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("rfd_clmd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_clmd_id")
	private En_Gstr9_RfdClmd rfdClmd;
	
	@JsonProperty("rfd_sanc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_sanc_id")
	private En_Gstr9_RfdSanc rfdSanc;
	
	@JsonProperty("rfd_rejt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_rejt_id")
	private En_Gstr9_RfdRejt rfdRejt;

	@JsonProperty("rfd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_pend_id")
	private En_Gstr9_RfdPend rfdPend;
	
	@JsonProperty("tax_dmnd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_dmnd_id")
	private En_Gstr9_TaxDmnd taxDmnd;

	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private En_Gstr9_TaxPaid taxPaid;

	@JsonProperty("dmnd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dmnd_pend_id")
	private En_Gstr9_DmndPend dmndPend;






}
