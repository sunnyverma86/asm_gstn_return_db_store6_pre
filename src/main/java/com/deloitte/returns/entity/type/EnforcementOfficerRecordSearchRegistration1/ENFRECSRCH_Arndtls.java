package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1;
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
@Table(name = "arndtls", schema = "enforcement_officer_RSRegis")

public class ENFRECSRCH_Arndtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("arn_state")
	private String arnstate;

	@JsonProperty("arn_status")
	private String arnstatus;

	@JsonProperty("financialYr")
	private String financialYr;

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("appln_cd")
	private String applncd;
	
	@JsonProperty("submit_dt")
	private String submitdt;


}
