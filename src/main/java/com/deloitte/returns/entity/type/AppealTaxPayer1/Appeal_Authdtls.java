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
@Table(name = "authdtls", schema = "appeal_by_tax_payer")

public class Appeal_Authdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("pl")
	private String pl;

	@JsonProperty("auth")
	private String auth;
	
	@JsonProperty("authdesig")
	private String authdesig;

	@JsonProperty("signty")
	private String signty;

	@JsonProperty("pan")
	private String pan;
	
	@JsonProperty("time")
	private String time;




}
