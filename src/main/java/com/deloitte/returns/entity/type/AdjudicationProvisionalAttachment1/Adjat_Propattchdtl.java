package com.deloitte.returns.entity.type.AdjudicationProvisionalAttachment1;

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
@Table(name = "propattchdtls", schema = "adjudication_provisional_attachment")
public class Adjat_Propattchdtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("attchid")
	private String attchid;

	@JsonProperty("attchnm")
	private String attchnm;

	@JsonProperty("locadd")
	private String locadd;

	@JsonProperty("propname")
	private String propname;
}