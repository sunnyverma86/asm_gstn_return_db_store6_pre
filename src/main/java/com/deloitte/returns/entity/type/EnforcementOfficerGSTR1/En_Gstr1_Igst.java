package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "igst", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Igst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fee")
	private Long fee;

	@JsonProperty("intr")
	private Long intr;

	@JsonProperty("oth")
	private Long oth;

	@JsonProperty("pen")
	private Long pen;

	@JsonProperty("tx")
	private Long tx;

}
