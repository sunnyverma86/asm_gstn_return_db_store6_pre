package com.deloitte.returns.entity.type.RecoveryGetCaseData;
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
@Table(name = "rcrydtl", schema = "recovery_get_case_data")

public class Rcmor_Rcrydtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("mor")
	private String mor;

	@JsonProperty("certodtyp")
	private String certodtyp;
	
	@JsonProperty("maindocs")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maindocs_id")
	private Rcmor_Maindocs maindocs;




}
