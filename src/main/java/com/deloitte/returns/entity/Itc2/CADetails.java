package com.deloitte.returns.entity.Itc2;

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
@Table(name = "caDetails", schema = "itc02")
@Entity
public class CADetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cadt")
	private String cadt;

	@JsonProperty("caname")
	private String caname;

	@JsonProperty("doc_id")
	private String docID;

	@JsonProperty("firm")
	private String firm;

	@JsonProperty("member")
	private String member;
	
	@JsonProperty("hash")
	private String hash;

}