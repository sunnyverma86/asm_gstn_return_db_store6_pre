package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "appelperherdata", schema = "appeal_by_tax_payer")

public class Appeal_Appelperherdata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("sdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private Appeal_Sdtls sdtls;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Appeal_Todtls todtls;
	
	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelperherdata_id")
	private List<Appeal_Suppdocs> suppdocs;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelperherdata_id")
	private List<Appeal_Maindocs> maindocs;

	@JsonProperty("docupdtl")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelperherdata_id")
	private List<Appeal_Docupdtl> docupdtl;




}
