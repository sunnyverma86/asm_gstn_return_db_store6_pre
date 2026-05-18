package com.deloitte.returns.entity.Gstr9c;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cert_textpartb1", schema = "gstr9c")
public class Gstr9c_CERTTextpartb1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("info_stat")
	private String infoStat;

	@JsonProperty("isagree")
	private String isagree;

	@JsonProperty("ishave")
	private String ishave;

	@JsonProperty("add_addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_addr_id")
	private Gstr9c_AddAddr addAddr;

	@JsonProperty("principal_addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "principal_addr_id")
	private Gstr9c_PrincipalAddr principalAddr;

	@JsonProperty("qualifications")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_textpartb1_id")
	private List<Gstr9c_Qualifications> qualifications;
}
