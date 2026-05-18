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
@Table(name = "pdcash", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_Pdcash {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("liab_ldg_id")
	private Double liabLdgId;

	@JsonProperty("c_intrpd")
	private Double cIntrpd;

	@JsonProperty("spd")
	private Double spd;

	@JsonProperty("i_lfeepd")
	private Double iLfeepd;

	@JsonProperty("cspd")
	private Double cspd;

	@JsonProperty("s_intrpd")
	private Double sIntrpd;

	@JsonProperty("trans_typ")
	private Integer transTyp;

	@JsonProperty("i_intrpd")
	private Double iIntrpd;

	@JsonProperty("s_lfeepd")
	private Double sLfeepd;

	@JsonProperty("c_lfeepd")
	private Double cLfeepd;

	@JsonProperty("cpd")
	private Double cpd;

	@JsonProperty("cs_intrpd")
	private Double csIntrpd;

	@JsonProperty("cs_lfeepd")
	private Double csLfeepd;

	@JsonProperty("ipd")
	private Double ipd;

}
