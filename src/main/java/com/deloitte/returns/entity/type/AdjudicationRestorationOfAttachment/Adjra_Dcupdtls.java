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
@Table(name = "dcupdtls", schema = "adjudication_restoration_attachment")

public class Adjra_Dcupdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dcupdtls_id;

	@Column(name = "docttl")
	@JsonProperty("docttl")
	private String docttl;
	
	@Column(name = "docName")
	@JsonProperty("docName")
	private String docName;

	@Column(name = "id")
	@JsonProperty("id")
	private String id;

	@Column(name = "ct")
	@JsonProperty("ct")
	private String ct;

	@Column(name = "ty")
	@JsonProperty("ty")
	private String ty;

	@Column(name = "hash")
	@JsonProperty("hash")
	private String hash;



}
