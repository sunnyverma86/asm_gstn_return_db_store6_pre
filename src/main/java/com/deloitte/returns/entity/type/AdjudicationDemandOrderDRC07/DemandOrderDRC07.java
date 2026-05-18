package com.deloitte.returns.entity.type.AdjudicationDemandOrderDRC07;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demand_order", schema = "demand_order_drc07")
public class DemandOrderDRC07 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("reqtyp")
	private String reqtyp;

	@JsonProperty("trdnm")
	private String trdnm;

	@JsonProperty("demandid")
	private String demandid;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("legnm")
	private String legnm;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("demanddt")
	private String demanddt;
//data type
	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private DEMANDDTLS_Todtls todtls;

	@JsonProperty("asmtorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "asmtorder_id")
	private DEMANDDTLS_Asmtorder asmtorder;
	
	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;

	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

}