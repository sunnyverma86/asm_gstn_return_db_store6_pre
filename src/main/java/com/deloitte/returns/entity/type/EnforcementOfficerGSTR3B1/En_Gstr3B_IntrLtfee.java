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
@Table(name = "intr_ltfee", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_IntrLtfee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("intr_details")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_details_id")
	private En_Gstr3B_IntrDetails intrDetails;

	@JsonProperty("ltfee_details")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ltfee_details_id")
	private En_Gstr3B_LtfeeDetails ltfeeDetails;

}
