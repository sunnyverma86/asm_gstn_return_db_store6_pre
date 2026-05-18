
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
@Table(name = "rsad", schema = "registds")
public class TdsTcs_Rsad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bnm")
	public String bnm;

	@JsonProperty("bno")
	public String bno;

	@JsonProperty("dst")
	public String dst;

	@JsonProperty("flno")
	public String flno;

	@JsonProperty("loc")
	public String loc;

	@JsonProperty("pncd")
	public String pncd;

	@JsonProperty("st")
	public String st;

	@JsonProperty("stcd")
	public String stcd;

	@JsonProperty("cnty")
	public String cnty;

}
