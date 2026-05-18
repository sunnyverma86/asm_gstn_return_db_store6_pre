package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "sdtls", schema = "appeal_by_tax_payer")

public class Appeal_Sdtls {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcprevstat")
	private String ntcprevstat;
	
	@JsonProperty("recpofnotce")
	private String recpofnotce;

	@JsonProperty("notcplace")
	private String notcplace;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("venu")
	private String venu;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("ntcsubj")
	private String ntcsubj;
	
	@JsonProperty("prevphdt")
	private String prevphdt;




}
