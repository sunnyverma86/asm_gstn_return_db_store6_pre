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
@Table(name = "paadifr2", schema = "adjudication_provisional_assessment")
public class Adjpa_Paadifr2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("ordrno")
	private String ordrno;

	@JsonProperty("ordrdt")
	private String ordrdt;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("venu")
	private String venu;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paadifr2_id")
	private List<Adjpa_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paadifr2_id")
	private List<Adjpa_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paadifr2_id")
	private List<Adjpa_Annxdocs> annxdocs;

}
