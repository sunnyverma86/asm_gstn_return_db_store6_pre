package com.deloitte.returns.entity.type.AppealTaxDepartment1;
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
@Table(name = "issuecat", schema = "appeal_by_tax_department")

public class Apltd_Issuecat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jsonIdIssuecat; // Renamed to avoid conflict

	@JsonProperty("id")
	public String getJsonIdIssuecat() {
		return jsonIdIssuecat;
	}

	@JsonProperty("id")
	public void setJsonIdIssuecat(String jsonIdIssuecat) {
		this.jsonIdIssuecat = jsonIdIssuecat;
	}

	@JsonProperty("desc")
	private String desc;



}
