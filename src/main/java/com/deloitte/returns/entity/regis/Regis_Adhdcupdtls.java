package com.deloitte.returns.entity.regis;

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
@Table(name = "adhdcupdtls", schema = "regis")
public class Regis_Adhdcupdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonProperty("ct")
	private String ct;

	@JsonProperty("addrId")
	private long addrID;


	private String jsonIdAdhdcupdtls; // Renamed to avoid conflict

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("ty")
	private String ty;
	
	@JsonProperty("docName")
	private String docName;
	

	@JsonProperty("id")
	public String getJsonIdAdhdcupdtls() {
		return jsonIdAdhdcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdAdhdcupdtls(String jsonIdAdhdcupdtls) {
		this.jsonIdAdhdcupdtls = jsonIdAdhdcupdtls;
	}
	
	@JsonProperty("existingDoc")
	private String existingDoc;

}
