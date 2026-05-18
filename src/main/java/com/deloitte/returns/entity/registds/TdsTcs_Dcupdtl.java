
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
@Table(name = "dcupdtls", schema = "registds")
public class TdsTcs_Dcupdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	public String ct;

	@JsonProperty("hash")
	public String hash;


	private String jsonIdDcupdtls; // Renamed to avoid conflict
	
	@JsonProperty("id")
	public String getJsonIdDcupdtls() {
		return jsonIdDcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtls(String jsonIdDcupdtls) {
		this.jsonIdDcupdtls = jsonIdDcupdtls;
	}

	@JsonProperty("ty")
	public String ty;

}
