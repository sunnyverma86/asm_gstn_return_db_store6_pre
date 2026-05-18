package com.deloitte.returns.entity.type.RecoveryGetCaseData1;
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
@Table(name = "rcrcrtordata", schema = "recovery_get_case_data")

public class Rcmor_Rcrcrtordata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("rcrydtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rcrydtl_id")
	private Rcmor_Rcrydtl rcrydtl;
	
	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Rcmor_Todtls todtls;



}
