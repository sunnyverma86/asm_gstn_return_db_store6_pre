package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "todtls", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Todtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dg")
	private String dg;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("nm")
	private String nm;

	@JsonProperty("pl")
	private String pl;

	@JsonProperty("pn")
	private String pn;

	@JsonProperty("signty")
	private String signty;

	@JsonProperty("toid")
	private String toid;

}
