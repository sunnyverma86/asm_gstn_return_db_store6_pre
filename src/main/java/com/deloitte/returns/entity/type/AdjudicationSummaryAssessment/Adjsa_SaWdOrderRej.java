package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

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
@Table(name = "saWdOrderRej", schema = "adjudication_summary_assessment")
public class Adjsa_SaWdOrderRej {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("saWdOrder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saWdOrder_id")
	private Adjsa_SaWdOrder saWdOrder;

	@JsonProperty("saWdOrderRej")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saWdOrderRej_id")
	private Adjsa_SaWdOrderRej saWdOrderRej;

}
