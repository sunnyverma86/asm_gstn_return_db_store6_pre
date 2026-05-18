package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "table15", schema = "gstr9a")
@Entity
public class Gstr9a_Table15 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("dmnd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dmnd_pend_id")
	private Gstr9a_DmndPend dmndPend;

	@JsonProperty("rfd_clmd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_clmd_id")
	private Gstr9a_RfdClmd rfdClmd;

	@JsonProperty("rfd_pend")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_pend_id")
	private Gstr9a_RfdPend rfdPend;

	@JsonProperty("rfd_rejt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_rejt_id")
	private Gstr9a_RfdRejt rfdRejt;

	@JsonProperty("rfd_sanc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfd_sanc_id")
	private Gstr9a_RfdSanc rfdSanc;

	@JsonProperty("tax_dmnd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_dmnd_id")
	private Gstr9a_TaxDmnd taxDmnd;

	@JsonProperty("txpd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "txpd_id")
	private Gstr9a_Txpd txpd;

}