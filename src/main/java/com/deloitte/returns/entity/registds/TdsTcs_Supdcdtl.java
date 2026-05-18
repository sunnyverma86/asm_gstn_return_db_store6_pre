
package com.deloitte.returns.entity.registds;

import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ct", "hash", "id", "ty" })
@Generated("jsonschema2pojo")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supdcdtls", schema = "registds")
public class TdsTcs_Supdcdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSupdcdtl;

	@JsonProperty("ct")
	public String ct;

	@JsonProperty("hash")
	public String hash;

	private String jsonIdSupdcdtls; // Renamed to avoid conflict
	
	@JsonProperty("id")
	public String getjsonIdSupdcdtls() {
		return jsonIdSupdcdtls;
	}

	@JsonProperty("id")
	public void setjsonIdSupdcdtls(String jsonIdSupdcdtls) {
		this.jsonIdSupdcdtls = jsonIdSupdcdtls;
	}

	@JsonProperty("ty")
	public String ty;

}
