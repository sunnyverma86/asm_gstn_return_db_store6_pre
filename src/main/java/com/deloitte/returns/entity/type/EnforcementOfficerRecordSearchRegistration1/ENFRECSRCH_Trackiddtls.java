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
@Table(name = "trackiddtls", schema = "enforcement_officer_RSRegis")

public class ENFRECSRCH_Trackiddtls {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("trackID")
	private String trackID;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("memberType")
	private String memberType;

	@JsonProperty("panNum")
	private String panNum;

	@JsonProperty("ppNum")
	private String ppNum;
	
	@JsonProperty("email")
	private String email;

	@JsonProperty("mobNo")
	private String mobNo;

	@JsonProperty("action")
	private String action;

	@JsonProperty("boOffNm")
	private String boOffNm;

	
}
