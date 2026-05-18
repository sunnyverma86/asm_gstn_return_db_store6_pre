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
@Table(name = "eco_dtls", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_EcoDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eco_reg_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eco_reg_sup_id")
	private En_Gstr3B_EcoRegSup ecoRegSup;

	@JsonProperty("eco_sup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eco_sup_id")
	private En_Gstr3B_EcoSup ecoSup;

}
