
package com.deloitte.returns.entity.registds;

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
@Table(name = "decdtls", schema = "registds")
public class TdsTcs_Decdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("asdes")
	public String asdes;

	@JsonProperty("asnm")
	public String asnm;

	@JsonProperty("dt")
	public String dt;

	@JsonProperty("pl")
	public String pl;

	@JsonProperty("signty")
	public String signty;

}
