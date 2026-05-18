package com.deloitte.returns.entity.Gstr6;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "doclst", schema = "gstr6")
@Entity
public class Gstr6_Doclst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camtc")
	private Double camtc;

	@JsonProperty("camti")
	private Double camti;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("crddt")
	private String crddt;

	@JsonProperty("crdnum")
	private String crdnum;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("docdt")
	private String docdt;

	@JsonProperty("docnum")
	private String docnum;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("iamtc")
	private Double iamtc;

	@JsonProperty("iamts")
	private Double iamti;

	@JsonProperty("iamts")
	private Double iamts;

	@JsonProperty("isd_docty")
	private String isdDocty;

	@JsonProperty("samti")
	private Double samti;

	@JsonProperty("samts")
	private Double samts;

}