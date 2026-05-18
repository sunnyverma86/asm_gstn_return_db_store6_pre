package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1;
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
@Table(name = "enforcement_officer_record_search_returns", schema = "enforcement_officer_RSR")

public class EnforcementOfficerRecordSearchReturns {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("stcd")
	private String stcd;

		
	@JsonProperty("rtnList")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_record_search_returns_id")
	private List<ENFRECSRCH_RtnList> rtnList;


}
