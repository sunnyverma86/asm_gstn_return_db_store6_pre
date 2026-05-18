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
@Table(name = "isda", schema = "enforcement_officer_gstr2a")
public class En_Gstr2A_Isda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cfs")
	private String cfs;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("doclist")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "isda_id")
	private List<En_Gstr2A_Doclist> doclist;

}