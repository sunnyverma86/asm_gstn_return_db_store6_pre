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
@Table(name = "roReplydata", schema = "adjudication_rectification_orders")
public class Adjro_RoReplydata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("reply")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private Adjro_Reply reply;

}
