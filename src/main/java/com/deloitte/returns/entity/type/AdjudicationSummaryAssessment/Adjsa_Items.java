package com.deloitte.returns.entity.type.AdjudicationSummaryAssessment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "items", schema = "adjudication_summary_assessment")
public class Adjsa_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "item_name")
	@JsonProperty("itemName")
	private String itemName;

	@Column(name = "item_names")
	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("addIntimationData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addIntimationData_id")
	private Adjsa_AddIntimationData addIntimationData;

	@JsonProperty("saOrder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOrder_id")
	private Adjsa_SaOrder saOrder;
	
	@JsonProperty("saaddRecd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjsa_SaaddRecd> saaddRecd;
	
	@JsonProperty("saWdrawlOrder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjsa_SaWdrawlOrder> saWdrawlOrder;
	
	@JsonProperty("saWdrawlOrderRej")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjsa_SaWdrawlOrderRej> saWdrawlOrderRej;

}