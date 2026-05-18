
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

@Table(name = "bzdtls", schema = "registds")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TdsTcs_Bzdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cobz")
	public String cobz;

	@JsonProperty("ctjd")
	public String ctjd;

	@JsonProperty("cmbzdt")
	public String cmbzdt;

	@JsonProperty("rgfmdt")
	public String rgfmdt;

	@JsonProperty("stcd")
	public String stcd;

	@JsonProperty("govtype")
	public String govtype;

	@JsonProperty("stjd")
	public String stjd;

	@JsonProperty("trdnm")
	public String trdnm;

}
