package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "paappdata", schema = "adjudication_provisional_assessment")
public class Adjpa_Paappdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("rsn")
	@Column(length = 1000)
	private String rsn;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjpa_Decdtls decdtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paappdata_id")
	private List<Adjpa_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paappdata_id")
	private List<Adjpa_Maindocs> maindocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paappdata_id")
	private List<Adjpa_Annxdocs> annxdocs;

	@JsonProperty("commdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paappdata_id")
	private List<Adjpa_Commdtls> commdtls;

}
