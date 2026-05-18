package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1;
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
@Table(name = "rtnList", schema = "enforcement_officer_RSR")

public class ENFRECSRCH_RtnList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("arnNum")
	private String arnNum;

	@JsonProperty("formName")
	private String formName;
	
	@JsonProperty("filDate")
	private String filDate;

	@JsonProperty("rtnPd")
	private String rtnPd;


}
