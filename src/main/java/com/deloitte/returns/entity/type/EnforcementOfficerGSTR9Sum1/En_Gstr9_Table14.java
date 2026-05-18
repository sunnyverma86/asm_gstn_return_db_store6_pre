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
@Table(name = "table14", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table14 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("camt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camt_id")
	private En_Gstr9_Camt Camt;
	
	@JsonProperty("samt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "samt_id")
	private En_Gstr9_Samt Samt;

	@JsonProperty("csamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "csamt_id")
	private En_Gstr9_Csamt Csamt;
	
	@JsonProperty("iamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iamt_id")
	private En_Gstr9_Iamt Iamt;

	@JsonProperty("intr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_id")
	private En_Gstr9_Intr Intr;




}
