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
@Table(name = "declaration", schema = "refund")
public class Refund_Declaration {

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

	private String jsonIdDocupdtl;

	@JsonProperty("id")
	public String getJsonIdDocupdtl() {
		return jsonIdDocupdtl;
	}

	@JsonProperty("id")
	public void setJsonIdDocupdtl(String jsonIdDocupdtl) {
		this.jsonIdDocupdtl = jsonIdDocupdtl;
	}

	@JsonProperty("docName")
	private String docName;

}
