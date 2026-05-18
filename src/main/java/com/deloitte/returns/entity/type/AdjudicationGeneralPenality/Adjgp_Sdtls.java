package com.deloitte.returns.entity.type.AdjudicationGeneralPenality;

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
@Table(name = "sdtls", schema = "adjudication_general_penality")
public class Adjgp_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	


	@JsonProperty("gpscnwoph")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscnwoph_id")
	private Adjgp_Gpscnwoph gpscnwoph;
	
	
	@JsonProperty("dtscn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtscn_id")
	private Adjgp_Dtscn dtscn;

	@JsonProperty("drprcdt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcdt_id")
	private Adjgp_Drprcdt drprcdt;

	@JsonProperty("drprcgp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcgp_id")
	private Adjgp_Drprcgp drprcgp;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjgp_Todtls todtls;

	@JsonProperty("dtorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorder_id")
	private Adjgp_Dtorder dtorder;
	
	@JsonProperty("gporder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gporder_id")
	private Adjgp_Gporder gporder;

}