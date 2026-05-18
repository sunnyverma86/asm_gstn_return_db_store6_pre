package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

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
@Table(name = "drprcsr", schema = "adjudication_scrutiny_returns")
public class Adjsr_Drprcsr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("type")
	private String type;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjsr_Tpovl tpovl;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcsr_id")
	private List<Adjsr_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcsr_id")
	private List<Adjsr_Maindocs> maindocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "drprcsr_id")
	private List<Adjsr_Dmddtls> dmddtls;

}
