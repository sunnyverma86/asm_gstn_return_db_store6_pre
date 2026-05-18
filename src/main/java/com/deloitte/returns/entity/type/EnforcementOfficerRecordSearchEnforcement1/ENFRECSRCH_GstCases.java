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
@Table(name = "gstCases", schema = "enforcement_officer_RSE")

public class ENFRECSRCH_GstCases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gstCases_id;

	@JsonProperty("date")
	private String date;

	@JsonProperty("amtTotal")
	private Double amtTotal;

	@JsonProperty("taxPeriod")
	private String taxPeriod;

	@JsonProperty("isCompAllowed")
	private String isCompAllowed;

	@JsonProperty("amtIgst")
	private Double amtIgst;

	@JsonProperty("arrestCount")
	private Double arrestCount;
	
	@JsonProperty("isScnIssued")
	private String isScnIssued;

	@JsonProperty("source")
	private String source;

	@JsonProperty("isProsInitiated")
	private String isProsInitiated;

	@JsonProperty("tradeName")
	private String tradeName;
	
	@JsonProperty("officeAddress")
	private String officeAddress;

	@JsonProperty("type")
	private String type;

	@JsonProperty("anyInspection")
	private String anyInspection;
	
	@JsonProperty("amtCgst")
	private Double amtCgst;

	@JsonProperty("amtOther")
	private Double amtOther;

	@JsonProperty("amtCess")
	private Double amtCess;
	
	@JsonProperty("contInvolved")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contInvolved_id")
	public ENFRECSRCH_ContInvolved contInvolved;
	
	@JsonProperty("anyArrest")
	private String anyArrest;

	@JsonProperty("bookedBy")
	private String bookedBy;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("amtSgst")
	private Double amtSgst;
	
	@JsonProperty("closeReason")
	private String closeReason;

	@JsonProperty("dataSource")
	private String dataSource;

	@JsonProperty("status")
	private String status;








}
