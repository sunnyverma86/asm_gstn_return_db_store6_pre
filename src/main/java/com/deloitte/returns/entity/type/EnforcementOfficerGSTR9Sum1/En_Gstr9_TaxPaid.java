package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taxPaid", schema = "enforcement_officer_gstr9")


public class En_Gstr9_TaxPaid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;
	
	@JsonProperty("intr")
	private Double intr;
	
	@JsonProperty("fee")
	private Double fee;
	
	@JsonProperty("pen")
	private Double pen;
	
	@JsonProperty("pd_by_cash")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private List<En_Gstr9_PDByCash> pdByCash;



}
