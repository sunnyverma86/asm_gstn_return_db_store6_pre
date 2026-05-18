package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enforcement_officer_record_search_registration", schema = "enforcement_officer_RSRegis")

public class EnforcementOfficerRecordSearchRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("ppbzdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ppbzdtls_id")
	private ENFRECSRCH_Ppbzdtls ppbzdtls;

	@JsonProperty("curr_auth")
	private String currauth;

	@JsonProperty("cntr_jursd_cd")
	private String cntrjursdcd;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("const_bus")
	private String constbus;

	@JsonProperty("lgbznm")
	private String lgbznm;
	
	@JsonProperty("state_jursd_cd")
	private String statejursdcd;

	@JsonProperty("gstin_status")
	private String gstinstatus;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("tpty")
	private String tpty;

	@JsonProperty("arndtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_record_search_registration_id")
	private List<ENFRECSRCH_Arndtls> arndtls;

	@JsonProperty("trackiddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_record_search_registration_id")
	private List<ENFRECSRCH_Trackiddtls> trackiddtls;



}
