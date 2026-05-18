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
@Table(name = "tx_py", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_TxPy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("trans_typ")
	private Long transTyp;

	@JsonProperty("trans_desc")
	private String transDesc;

	@JsonProperty("tran_desc")
	private String tranDesc;

	@JsonProperty("liab_ldg_id")
	private Long liabLdgID;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private En_Gstr3B_Sgst sgst;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private En_Gstr3B_Cgst cgst;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private En_Gstr3B_Cess cess;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private En_Gstr3B_Igst igst;

}