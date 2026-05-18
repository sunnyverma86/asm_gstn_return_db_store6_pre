package com.deloitte.returns.entity.type.AppealEffectManualApl04TranOrd1;
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
@Table(name = "appleffectorderdata", schema = "appeal_effect_manual_apl_tran_ord")

public class Aplto_Appleffectorderdata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("stateCd")
	private String stateCd;

	@JsonProperty("refId")
	private String refId;

	@JsonProperty("gstin")
	private String gstin;
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appleffectorderdata_id")
	private List<Aplto_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appleffectorderdata_id")
	private List<Aplto_Suppdocs> suppdocs;

	@JsonProperty("manualOrderDoc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appleffectorderdata_id")
	private List<Aplto_ManualOrderDoc> manualOrderDoc;
	
	@JsonProperty("sdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdtls_id")
	private Aplto_Sdtls sdtls;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Aplto_Todtls todtls;




}
