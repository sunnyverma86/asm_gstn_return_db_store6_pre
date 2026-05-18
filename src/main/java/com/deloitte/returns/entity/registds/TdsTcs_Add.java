
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
@Table(name = "add", schema = "registds")
public class TdsTcs_Add {

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

	@JsonProperty("lg")
	public String lg;

	@JsonProperty("loc")
	public String loc;

	@JsonProperty("lt")
	public String lt;

	@JsonProperty("pncd")
	public String pncd;

	@JsonProperty("st")
	public String st;

	@JsonProperty("stcd")
	public String stcd;

}
