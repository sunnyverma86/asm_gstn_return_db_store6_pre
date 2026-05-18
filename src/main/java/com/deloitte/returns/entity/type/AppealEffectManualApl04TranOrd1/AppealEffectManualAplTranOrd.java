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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appeal_effect_manual_apl_tran_ord", schema = "appeal_effect_manual_apl_tran_ord")

public class AppealEffectManualAplTranOrd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("casety")
	private String casety;

	@JsonProperty("crn")
	private String crn;

	@JsonProperty("dof")
	private String dof;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("state_cd")
	private String stateCD;

	@JsonProperty("status_desc")
	private String statusDesc;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appeal_effect_manual_apl_tran_ord_id")
	private List<Aplto_Items> items;



}
