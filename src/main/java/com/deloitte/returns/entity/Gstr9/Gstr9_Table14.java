package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table14", schema = "gstr9")
@Entity
public class Gstr9_Table14 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;
	
	
	@JsonProperty("camt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camt_id")
	private Gstr9_Camt txpaidCamt;
	
	@JsonProperty("samt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "samt_id")
	private Gstr9_Samt txpaidSamt;

	@JsonProperty("csamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "csamt_id")
	private Gstr9_Csamt txpaidCsamt;

	@JsonProperty("fee")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fee_id")
	private Gstr9_Fee txpaidFee;

	@JsonProperty("iamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iamt_id")
	private Gstr9_Iamt txpaidIamt;

	@JsonProperty("intr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_id")
	private Gstr9_Intr txpaidIntr;


	@JsonProperty("dmnd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dmnd_pend_id")
	private Gstr9_DmndPend dmndPend;

	@JsonProperty("rfd_clmd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_clmd_id")
	private Gstr9_RfdClmd rfdClmd;

	@JsonProperty("rfd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_pend_id")
	private Gstr9_RfdPend rfdPend;

	@JsonProperty("rfd_rejt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_rejt_id")
	private Gstr9_RfdRejt rfdRejt;

	@JsonProperty("rfd_sanc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_sanc_id")
	private Gstr9_RfdSanc rfdSanc;

	@JsonProperty("tax_dmnd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_dmnd_id")
	private Gstr9_TaxDmnd taxDmnd;

}
