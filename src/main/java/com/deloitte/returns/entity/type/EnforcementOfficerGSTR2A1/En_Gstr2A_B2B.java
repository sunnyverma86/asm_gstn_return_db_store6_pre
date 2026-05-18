package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

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
@Table(name = "b2b", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_B2B {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cfs")
	private String cfs;

	@JsonProperty("cfs3b")
	private String cfs3B;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("dtcancel")
	private String dtcancel;

	@JsonProperty("fldtr1")
	private String fldtr1;

	@JsonProperty("flprdr1")
	private String flprdr1;
	

	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private List<En_Gstr2A_Inv> inv;
	
	

}
