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
@Table(name = "propattchdtls", schema = "adjudication_restoration_attachment")

public class Adjra_Propattchdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "attchnm")
	@JsonProperty("attchnm")
	private String attchnm;

	@Column(name = "attchid")
	@JsonProperty("attchid")
	private String attchid;

	
	@Column(name = "propname")
	@JsonProperty("propname")
	private String propname;

	@Column(name = "locadd")
	@JsonProperty("locadd")
	private String locadd;


}
