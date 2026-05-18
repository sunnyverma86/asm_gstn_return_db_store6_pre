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
@Table(name = "dispamt", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Dispamt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private En_Gstr1_Cess cess;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private En_Gstr1_Cgst cgst;

	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private En_Gstr1_Igst igst;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private En_Gstr1_Sgst sgst;

	@JsonProperty("total")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_id")
	private En_Gstr1_Total total;

}