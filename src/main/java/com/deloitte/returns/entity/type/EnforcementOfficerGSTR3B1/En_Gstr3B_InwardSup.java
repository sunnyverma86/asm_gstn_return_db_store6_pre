package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

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
@Table(name = "inward_sup", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_InwardSup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("isup_details")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inward_sup_id")
	private List<En_Gstr3B_IsupDetail> isupDetails;

}