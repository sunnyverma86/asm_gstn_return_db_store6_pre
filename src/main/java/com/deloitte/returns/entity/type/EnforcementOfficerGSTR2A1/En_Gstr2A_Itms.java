package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

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
@Table(name = "itms", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Itms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("num")
	private long num;

	@JsonProperty("itm_det")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itm_det_id")
	private En_Gstr2A_ItmDet itmDet;

}
