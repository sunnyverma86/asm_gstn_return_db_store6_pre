package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enforcement_officer_record_search_enforcement", schema = "enforcement_officer_RSE")

public class EnforcementOfficerRecordSearchEnforcement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstCases")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_record_search_enforcement_id")
	private List<ENFRECSRCH_GstCases> gstCases;

	@JsonProperty("preGstCases")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_record_search_enforcement_id")
	private List<ENFRECSRCH_PreGstCases> preGstCases;

	
}
