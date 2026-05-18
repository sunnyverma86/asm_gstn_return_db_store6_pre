package com.deloitte.returns.entity.type.AdjudicationRestorationOfAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
@Table(name = "attchdtls", schema = "adjudication_restoration_attachment")

public class Adjra_Attchdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "accntty")
	@JsonProperty("accntty")
	private String accntty;

	@Column(name = "attchnm")
	@JsonProperty("attchnm")
	private String attchnm;

	@Column(name = "attchNum")
	@JsonProperty("attchNum")
	private String attchNum;

	@Column(name = "propAdd")
	@JsonProperty("propAdd")
	private String propAdd;


}
