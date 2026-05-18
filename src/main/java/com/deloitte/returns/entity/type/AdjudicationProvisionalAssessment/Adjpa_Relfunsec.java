package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relfunsec", schema = "adjudication_provisional_assessment")
public class Adjpa_Relfunsec {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("ordrefno")
	private String ordrefno;

	@JsonProperty("dateoford")
	private String dateoford;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjpa_Decdtls decdtls;

	@JsonProperty("bankdetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "relfunsec_id")
	private List<Adjpa_Bankdetails> bankdetails;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "relfunsec_id")
	private List<Adjpa_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "relfunsec_id")
	private List<Adjpa_Maindocs> maindocs;

}
