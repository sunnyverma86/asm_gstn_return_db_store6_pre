package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

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
@Table(name = "asmtorder", schema = "demand_order_drc07")
public class DEMANDDTLS_Asmtorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("orddt")
	private String orddt;

	@JsonProperty("duedt")
	private String duedt;

	@JsonProperty("ordno")
	private String ordno;

	@JsonProperty("cmt1")
	private String cmt1;

	@JsonProperty("isuinv")
	private String isuinv;

	@JsonProperty("tp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tp_id")
	private DEMANDDTLS_Tp tp;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private DEMANDDTLS_Gdssvcdtls gdssvcdtls;

	@JsonProperty("dmddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asmtorder_id")
	private List<DEMANDDTLS_Dmddtls> dmddtls;

}