
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
@Table(name = "rgdtls", schema = "registds")
public class TdsTcs_Rgdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("panDate")
	private String panDate;// new

	@JsonProperty("aplst")
	public String aplst;

	@JsonProperty("aplty")
	public String aplty;

	@JsonProperty("asnm")
	public String asnm;

	@JsonProperty("pt")
	public String pt;

	@JsonProperty("aspan")
	public String aspan;

	@JsonProperty("em")
	public String em;

	@JsonProperty("lgbznm")
	public String lgbznm;

	@JsonProperty("mbno")
	public String mbno;

	@JsonProperty("moddt")
	public String moddt;

	@JsonProperty("pan")
	public String pan;

	@JsonProperty("stcd")
	public String stcd;

	@JsonProperty("tan")
	public String tan;

}
