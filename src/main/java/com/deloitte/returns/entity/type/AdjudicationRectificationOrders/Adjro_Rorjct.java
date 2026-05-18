package com.deloitte.returns.entity.type.AdjudicationRectificationOrders;

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
@Table(name = "rorjct", schema = "adjudication_rectification_orders")
public class Adjro_Rorjct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("type")
	private String type;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjro_Tpovl tpovl;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorjct_id")
	private List<Adjro_Annxdocs> annxdocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorjct_id")
	private List<Adjro_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorjct_id")
	private List<Adjro_Dmddtls> dmddtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorjct_id")
	private List<Adjro_Maindocs> maindocs;

}