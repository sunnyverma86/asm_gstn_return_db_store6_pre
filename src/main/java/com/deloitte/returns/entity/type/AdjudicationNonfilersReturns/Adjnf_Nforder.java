package com.deloitte.returns.entity.type.AdjudicationNonfilersReturns;

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
@Table(name = "nforder", schema = "adjudication_nonfilers_returns")
public class Adjnf_Nforder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("transTypeCd")
	private String transTypeCD;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("isuinv")
	private List<String> isuinv;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Adjnf_Gdssvcdtls gdssvcdtls;

	@JsonProperty("tpovl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpovl_id")
	private Adjnf_Tpovl tpovl;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nforder_id")
	private List<Adjnf_Suppdocs> suppdocs;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nforder_id")
	private List<Adjnf_Dmddtls> dmddtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nforder_id")
	private List<Adjnf_Maindocs> maindocs;

	@JsonProperty("rtntyp")
	private String rtntyp;

	@JsonProperty("othrsn")
	private String othrsn;

}
