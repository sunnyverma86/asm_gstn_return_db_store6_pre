package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docdtls", schema = "refund")
public class Refund_Docdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ct")
	private String ct;

	@JsonProperty("docttl")
	private String docttl;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("ty")
	private String ty;

	// @JsonProperty("id")
	private String jsonIdDocdtl;

	@JsonProperty("id")
	public String getJsonIdDocdtl() {
		return jsonIdDocdtl;
	}

	@JsonProperty("id")
	public void setJsonIdDocdtl(String jsonIdDocdtl) {
		this.jsonIdDocdtl = jsonIdDocdtl;
	}

}