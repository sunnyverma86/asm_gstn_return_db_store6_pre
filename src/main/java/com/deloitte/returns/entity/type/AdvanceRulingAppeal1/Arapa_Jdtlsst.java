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
@Table(name = "jdtlsst", schema = "advance_ruling_appeal")

public class Arapa_Jdtlsst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("jur")
	private String jur;

	@JsonProperty("em")
	private String em;

	@JsonProperty("dsgn")
	private String dsgn;

	@JsonProperty("jrdnme")
	private String jrdnme;

	@JsonProperty("bsad")
	private String bsad;


}
