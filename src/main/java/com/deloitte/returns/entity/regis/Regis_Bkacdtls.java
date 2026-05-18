package com.deloitte.returns.entity.regis;

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
@Table(name = "bkacdtls", schema = "regis")
public class Regis_Bkacdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eid")
	private String eid;

	@JsonProperty("acctVerifiedDt")
	private String acctVerifiedDt;

	@JsonProperty("bknm")
	private String bknm;

	@JsonProperty("acty")
	private String acty;

	@JsonProperty("validSource")
	private String validSource;

	@JsonProperty("acno")
	private String acno;

	@JsonProperty("validPrimAccHoldName")
	private String validPrimAccHoldName;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("validStatus")
	private String validStatus;

	@JsonProperty("bankStatus")
	private String bankStatus;

	@JsonProperty("bankRemarks")
	private String bankRemarks;

	@JsonProperty("ifsc")
	private String ifsc;

	@JsonProperty("validPrimAccHoldPan")
	private String validPrimAccHoldPan;

	@JsonProperty("brad")
	private String brad;

	@JsonProperty("acty_oth")
	private String actyOth;
	
	@JsonProperty("updateDt")
	private String updateDt;
	
	@JsonProperty("amendrsn")
	private String amendrsn;
	
	

	@JsonProperty("dcupdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private Regis_Dcupdtls dcupdtls;

}
