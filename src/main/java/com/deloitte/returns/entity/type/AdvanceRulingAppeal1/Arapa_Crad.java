package com.deloitte.returns.entity.type.AdvanceRulingAppeal1;
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
@Table(name = "crad", schema = "advance_ruling_appeal")

public class Arapa_Crad {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bno")
	private String bno;

	@JsonProperty("bnm")
	private String bnm;

	@JsonProperty("st")
	private String st;

	@JsonProperty("loc")
	private String loc;

	@JsonProperty("flno")
	private String flno;

	@JsonProperty("dst")
	private String dst;
	
	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("pncd")
	private String pncd;


}
