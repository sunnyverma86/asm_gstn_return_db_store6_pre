package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

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
@Table(name = "dcupdtls", schema = "adjudication_voluntary_payment")
public class Adjvp_Dcupdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	private String ct;

	@JsonProperty("docName")
	private String docName;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("ty")
	private String ty;

	private String jsonIdDcupdtls; // Renamed to avoid conflict

	@JsonProperty("id")
	public String getJsonIdDcupdtls() {
		return jsonIdDcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtls(String jsonIdDcupdtls) {
		this.jsonIdDcupdtls = jsonIdDcupdtls;
	}
	
	@JsonProperty("docttl")
	private String docttl;


}
