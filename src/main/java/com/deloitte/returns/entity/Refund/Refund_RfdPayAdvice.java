package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rfdPayAdvice", schema = "refund")
public class Refund_RfdPayAdvice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemName")
	private String itemName;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("rfdNoticeData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdNoticeData_id")
	private Refund_RfdNoticeData rfdNoticeData;

	@JsonProperty("refId")
	private String refID;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("rfdPmtAdvOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdPmtAdvOrderData_id")
	private Refund_RfdPmtAdvOrderData rfdPmtAdvOrderData;

}
