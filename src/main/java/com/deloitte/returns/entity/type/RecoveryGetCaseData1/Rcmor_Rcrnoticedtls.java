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
@Table(name = "rcrnoticedtls", schema = "recovery_get_case_data")

public class Rcmor_Rcrnoticedtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("rcrnoticedata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rcrnoticedata_id")
	private Rcmor_Rcrnoticedata rcrnoticedata;


}
