package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

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
@Table(name = "tds", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Tds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("amt_ded")
	private long amtDed;

	@JsonProperty("camt")
	private long camt;

	@JsonProperty("gstin_ded")
	private String gstinDed;

	@JsonProperty("iamt")
	private long iamt;

	@JsonProperty("samt")
	private long samt;

}