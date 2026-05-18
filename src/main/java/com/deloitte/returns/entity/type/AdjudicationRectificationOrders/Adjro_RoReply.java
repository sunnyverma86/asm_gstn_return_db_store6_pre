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
@Table(name = "roReply", schema = "adjudication_rectification_orders")
public class Adjro_RoReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refId")
	private String refId;

	@JsonProperty("roReplydata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roReplydata_id")
	private Adjro_RoReplydata roReplydata;

}