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
@Table(name = "docupdtl", schema = "appeal_by_tax_payer")

public class Appeal_Docupdtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	private String ct;
	
	@JsonProperty("docName")
	private String docName;

	@JsonProperty("ty")
	private String ty;

	@JsonProperty("hash")
	private String hash;

	private String jsonIdDocupdtl; // Renamed to avoid conflict

	@JsonProperty("id")
	public String getJsonIdDocupdtl() {
		return jsonIdDocupdtl;
	}

	@JsonProperty("id")
	public void setJsonIdDocupdtl(String jsonIdDocupdtl) {
		this.jsonIdDocupdtl = jsonIdDocupdtl;
	}


}
