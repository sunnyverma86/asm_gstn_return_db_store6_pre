package com.deloitte.returns.entity.type.AdjudicationRectificationOrders;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sdtls", schema = "adjudication_rectification_orders")
public class Adjro_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("rorect")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorect_id")
	private Adjro_Rorect rorect;

	@JsonProperty("roadifr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roadifr_id")
	private Adjro_Roadifr roadifr;

	@JsonProperty("rorjct")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rorjct_id")
	private Adjro_Rorjct rorjct;

}
