package com.deloitte.returns.entity.type.AdjudicationRestorationOfAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
@Table(name = "decdtls", schema = "adjudication_restoration_attachment")

public class Adjra_Decdtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asnm")
	@JsonProperty("asnm")
	private String asnm;
	
	@Column(name = "asdes")
	@JsonProperty("asdes")
	private String asdes;

	@Column(name = "dt")
	@JsonProperty("dt")
	private String dt;

	@Column(name = "pan")
	@JsonProperty("pan")
	private String pan;

	
	@Column(name = "pl")
	@JsonProperty("pl")
	private String pl;


}
