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
@Table(name = "sup_details", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_SupDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("osup_det")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "osup_det_id")
	private En_Gstr3B_OsupDet osupDet;

	@JsonProperty("osup_zero")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "osup_zero_id")
	private En_Gstr3B_OsupZero osupZero;

	@JsonProperty("osup_nil_exmp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "osup_nil_exmp_id")
	private En_Gstr3B_OsupNilExmp osupNilExmp;

	@JsonProperty("isup_rev")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isup_rev_id")
	private En_Gstr3B_IsupRev isupRev;

	@JsonProperty("osup_nongst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "osup_nongst_id")
	private En_Gstr3B_OsupNongst osupNongst;
	
	
	

}