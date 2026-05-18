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
@Table(name = "recorderdata", schema = "appeal_revision_order")

public class Rvord_Recorderdata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private Rvord_Sdtls sdtls;
	
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

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Rvord_Todtls todtls;



}
