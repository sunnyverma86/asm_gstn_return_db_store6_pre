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
@Table(name = "predepositpenality", schema = "appeal_by_tax_payer")

public class Appeal_Predepositpenality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("total")
	private Double total;

	@JsonProperty("percent")
	private Double percent;
	
	
}
