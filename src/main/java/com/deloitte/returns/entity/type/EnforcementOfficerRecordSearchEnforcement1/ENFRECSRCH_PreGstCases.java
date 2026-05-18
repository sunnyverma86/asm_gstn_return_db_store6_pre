package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preGstCases", schema = "enforcement_officer_RSE")

public class ENFRECSRCH_PreGstCases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long preGstCases_id;

	@JsonProperty("regNo")
	private String regNo;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("amtTotal")
	private Double amtTotal;

	@JsonProperty("taxPeriod")
	private String taxPeriod;

	@JsonProperty("tradeName")
	private String tradeName;

	@JsonProperty("act")
	private String act;

	@JsonProperty("isProsecuted")
	private String isProsecuted;

	@JsonProperty("anyArrest")
	private String anyArrest;

	@JsonProperty("id")
	private String id;

	@JsonProperty("pan")
	private String pan;

	@JsonProperty("dataSource")
	private String dataSource;
	
	@JsonProperty("status")
	private String status;

	@JsonProperty("contInvolved")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contInvolved_id")
	public ENFRECSRCH_ContInvolved contInvolved;



}
