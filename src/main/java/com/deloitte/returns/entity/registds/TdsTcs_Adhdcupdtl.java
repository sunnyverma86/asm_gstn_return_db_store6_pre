package com.deloitte.returns.entity.registds;

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
@Table(name = "adhdcupdtls", schema = "registds")
public class TdsTcs_Adhdcupdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	public String ct;

	@JsonProperty("hash")
	public String hash;

	
	private String jsonIdAdhdcupdtls; // Renamed to avoid conflict
	
	@JsonProperty("id")
	public String getJsonIdAdhdcupdtls() {
		return jsonIdAdhdcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdAdhdcupdtls(String jsonIdAdhdcupdtls) {
		this.jsonIdAdhdcupdtls = jsonIdAdhdcupdtls;
	}

	@JsonProperty("ty")
	public String ty;

}
