package com.deloitte.returns.entity.type.AdjudicationProvisionalAttachment1;

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
@Table(name = "paorder", schema = "adjudication_provisional_attachment")
public class Adjat_Paorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("add")
	private String add;

	@JsonProperty("attchty")
	private String attchty;

	@JsonProperty("desig")
	private String desig;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjat_Tpovl tpovl;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private List<Adjat_Annxdocs> annxdocs;

	@JsonProperty("bankattchdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private List<Adjat_Bankattchdtl> bankattchdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private List<Adjat_Maindocs> maindocs;

	@JsonProperty("propattchdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private List<Adjat_Propattchdtl> propattchdtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private List<Adjat_Suppdocs> suppdocs;

}