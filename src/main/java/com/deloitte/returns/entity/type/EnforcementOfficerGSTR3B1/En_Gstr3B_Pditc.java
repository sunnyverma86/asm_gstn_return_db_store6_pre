package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pditc", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_Pditc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("s_pds")
	private double sPds;

	@JsonProperty("liab_ldg_id")
	private long liabLdgId;

	@JsonProperty("cs_pdcs")
	private double csPdcs;

	@JsonProperty("c_pdi")
	private long cPdi;

	@JsonProperty("s_pdi")
	private Long sPdi;

	@JsonProperty("i_pdi")
	private Long iPdi;

	@JsonProperty("c_pdc")
	private double cPdc;

	@JsonProperty("i_pdc")
	private long iPdc;

	@JsonProperty("i_pds")
	private Long iPds;

	@JsonProperty("trans_typ")
	private Long transTyp;
	


}
