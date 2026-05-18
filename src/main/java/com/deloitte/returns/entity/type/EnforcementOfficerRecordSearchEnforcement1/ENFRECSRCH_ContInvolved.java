package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1;
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
@Table(name = "contInvolved", schema = "enforcement_officer_RSE")

public class ENFRECSRCH_ContInvolved {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("otherOffence")
	private String otherOffence;

//	@JsonProperty("offences")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "contInvolved_id")
//	private List<ENFRECSRCH_Offences> offences;


}


