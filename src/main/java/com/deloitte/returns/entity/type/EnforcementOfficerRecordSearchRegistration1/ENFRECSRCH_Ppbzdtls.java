package com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1;
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
@Table(name = "ppbzdtls", schema = "enforcement_officer_RSRegis")

public class ENFRECSRCH_Ppbzdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private ENFRECSRCH_Addr addr;

	@JsonProperty("ntr")
	private String ntr;


}
