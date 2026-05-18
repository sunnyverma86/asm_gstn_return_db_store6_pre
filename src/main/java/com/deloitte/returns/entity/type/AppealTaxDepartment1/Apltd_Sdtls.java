package com.deloitte.returns.entity.type.AppealTaxDepartment1;
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
@Entity
@Table(name = "sdtls", schema = "appeal_by_tax_department")

public class Apltd_Sdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcsubj")
	private String ntcsubj;

	@JsonProperty("ntcprevstat")
	private String ntcprevstat;

	@JsonProperty("prevphdt")
	private String prevphdt;
	
	@JsonProperty("notcplace")
	private String notcplace;

	@JsonProperty("recpofnotce")
	private String recpofnotce;

	@JsonProperty("phdt")
	private String phdt;
	
	@JsonProperty("venu")
	private String venu;

	@JsonProperty("pht")
	private String pht;

	



}
