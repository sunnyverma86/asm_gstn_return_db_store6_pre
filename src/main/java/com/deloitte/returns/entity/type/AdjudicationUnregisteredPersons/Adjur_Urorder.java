package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;
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
@Table(name = "urorder", schema = "adjudication_unregistered_persons")

public class Adjur_Urorder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("lbltoreg")
	private String lbltoreg;

	@JsonProperty("transTypeCd")
	private String transTypeCd;
	
	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("sec")
	private String sec;
	
	@JsonProperty("fy")
	private String fy;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("ntcno")
	private String ntcno;
	
	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjur_Gdssvcdtls gdssvcdtls;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjur_Tpovl tpovl;
	
	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urorder_id")
	private List<Adjur_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urorder_id")
	private List<Adjur_Dmddtls> dmddtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urorder_id")
	private List<Adjur_Maindocs> maindocs;

	@JsonProperty("isuinv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urorder_id")
	private List<Adjur_Isuinv> isuinv;




	

}
