package com.deloitte.returns.entity.type.AdvanceRulingReference1;
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
@Table(name = "docupdtl", schema = "advance_ruling_reference")

public class Ararf_Docupdtl {
	
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
	
	private String jsonIdDcupdtl; // Renamed to avoid conflict

	@JsonProperty("id")
	public String getJsonIdDcupdtl() {
		return jsonIdDcupdtl;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtl(String jsonIdDcupdtl) {
		this.jsonIdDcupdtl = jsonIdDcupdtl;
	}

}
