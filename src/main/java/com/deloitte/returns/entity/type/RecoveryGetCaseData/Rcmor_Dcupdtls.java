package com.deloitte.returns.entity.type.RecoveryGetCaseData;
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
@Table(name = "dcupdtls", schema = "recovery_get_case_data")

public class Rcmor_Dcupdtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("docName")
	private String docName;

	@JsonProperty("ct")
	private String ct;

	@JsonProperty("ty")
	private String ty;
	
	@JsonProperty("hash")
	private String hash;
	
	@JsonProperty("docttl")
	private String docttl;
	
	private String jsonIdDcupdtls; // Renamed to avoid conflict

	@JsonProperty("id")
	public String getJsonIdDcupdtls() {
		return jsonIdDcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtls(String jsonIdDcupdtls) {
		this.jsonIdDcupdtls = jsonIdDcupdtls;
	}


}
