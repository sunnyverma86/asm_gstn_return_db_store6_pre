package com.deloitte.returns.entity.type.AdjudicationRestorationOfAttachment;
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
@Table(name = "radata", schema = "adjudication_restoration_attachment")

public class Adjra_Radata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ordrno")
	@JsonProperty("ordrno")
	private String ordrno;
	
	@Column(name = "ordrdt")
	@JsonProperty("ordrdt")
	private String ordrdt;

	@Column(name = "sec")
	@JsonProperty("sec")
	private String sec;

	@Column(name = "reason")
	@JsonProperty("reason")
	private String reason;

	@Column(name = "attchty")
	@JsonProperty("attchty")
	private String attchty;

	@Column(name = "add")
	@JsonProperty("add")
	private String add;

	@Column(name = "desig")
	@JsonProperty("desig")
	private String desig;
	
	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjra_Decdtls decdtls;
	
	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "radata_id")
	private List<Adjra_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "radata_id")
	private List<Adjra_Maindocs> maindocs;

	@JsonProperty("bankattchdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "radata_id")
	private List<Adjra_Bankattchdtls> bankattchdtls;

	@JsonProperty("propattchdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "radata_id")
	private List<Adjra_Propattchdtls> propattchdtls;

	@JsonProperty("attchdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "radata_id")
	private List<Adjra_Attchdtls> attchdtls;





}
