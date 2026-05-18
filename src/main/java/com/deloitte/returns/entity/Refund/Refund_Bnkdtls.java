package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bnkdtls", schema = "refund")
public class Refund_Bnkdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ifsc")
	private String ifsc;

	@JsonProperty("nam")
	private String nam;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("acctno")
	private String acctno;

	@JsonProperty("original")
	private String original;

	@JsonProperty("startdt")
	private String startdt;

	@JsonProperty("bnkadd")
	@Column(length = 500)
	private String bnkadd;

	@JsonProperty("acctype")
	private String acctype;
	
	
	@JsonProperty("refid")
	private String refid;
	
	@JsonProperty("enddt")
	private String enddt;
	

}