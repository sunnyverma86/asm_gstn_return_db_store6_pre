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
@Table(name = "mfydmdtls", schema = "appeal_by_tax_payer")

public class Appeal_Mfydmdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("finyr")
	private String finyr;

	@JsonProperty("act")
	private String act;

	@JsonProperty("pos")
	private String pos;
	
	@JsonProperty("tax")
	private Double tax;

	@JsonProperty("ist")
	private Double ist;

	@JsonProperty("pnlty")
	private Double pnlty;

	@JsonProperty("others")
	private Double others;

	@JsonProperty("fee")
	private Double fee;

	@JsonProperty("ttl")
	private Double ttl;



}
