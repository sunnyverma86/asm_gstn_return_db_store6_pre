package com.deloitte.returns.entity.regis;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dcupdtls", schema = "regis")
public class Regis_Dcupdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	private String ct;

	@JsonProperty("addrId")
	private long addrID;

	@JsonProperty("existingDoc")
	private String existingDoc;

	private String jsonIdDcupdtls; // Renamed to avoid conflict

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("ty")
	private String ty;
	

	private String gstinNumberManual;

	@JsonProperty("id")
	public String getJsonIdDcupdtls() {
		return jsonIdDcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtls(String jsonIdDcupdtls) {
		this.jsonIdDcupdtls = jsonIdDcupdtls;
	}

	@ManyToOne
	@JoinColumn(name = "gstr_id")
	private RegistrationNormalTaxPayer registrationNormalTaxPayer;

}
