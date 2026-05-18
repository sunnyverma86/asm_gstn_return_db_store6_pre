package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "sdtls", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Sdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aescn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aescn_id")
	private En_Gstr1_Aescn aescn;

	@JsonProperty("aedrcorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private En_Gstr1_Aedrcorder aedrcorder;

	@JsonProperty("aplorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aplorder_id")
	private En_Gstr1_Aplorder aplorder;

	@JsonProperty("paorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private En_Gstr1_Paorder paorder;

	@JsonProperty("remandedordr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private En_Gstr1_Remandedordr remandedordr;

}