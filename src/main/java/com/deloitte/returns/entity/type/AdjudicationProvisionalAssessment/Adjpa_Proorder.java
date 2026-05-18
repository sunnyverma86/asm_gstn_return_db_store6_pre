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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proorder", schema = "adjudication_provisional_assessment")
public class Adjpa_Proorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("relsecamt")
	private long relsecamt;

	@JsonProperty("relsecno")
	private String relsecno;

	@JsonProperty("relsecdt")
	private String relsecdt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("amtw")
	private String amtw;

	@JsonProperty("relsecamtw")
	private String relsecamtw;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "proorder_id")
	private List<Adjpa_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "proorder_id")
	private List<Adjpa_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "proorder_id")
	private List<Adjpa_Annxdocs> annxdocs;

}
