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
@Table(name = "dcupdtls", schema = "refund")
public class Refund_Dcupdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("ty")
	private String ty;
	
	@JsonProperty("ct")
	private String ct;

	@JsonProperty("docttl")
	private String docttl;

	@JsonProperty("hash")
	private String hash;

	private String jsonIdDcupdtls;

	@JsonProperty("id")
	public String getJsonIdDcupdtls() {
		return jsonIdDcupdtls;
	}

	@JsonProperty("id")
	public void setJsonIdDcupdtls(String jsonIdDcupdtls) {
		this.jsonIdDcupdtls = jsonIdDcupdtls;
	}
	
	@JsonProperty("docName")
	private String docName;

}