package com.deloitte.returns.entity.type.AppealRevisionOrders1;
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
@Table(name = "rvnoticedata", schema = "appeal_revision_order")

public class Rvord_Rvnoticedata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("legnm")
	private String legnm;
	
	@JsonProperty("sdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private Rvord_Sdtls sdtls;

	@JsonProperty("orddtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private Rvord_Orddtl orddtl;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Rvord_Todtls todtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rvnoticedata_id")
	private List<Rvord_Suppdocs> suppdocs;

	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rvnoticedata_id")
	private List<Rvord_Annxdocs> annxdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rvnoticedata_id")
	private List<Rvord_Maindocs> maindocs;


	
	

}
