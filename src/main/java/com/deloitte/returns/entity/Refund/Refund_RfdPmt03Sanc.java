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
@Table(name = "rfdPmt03Sanc", schema = "refund")
public class Refund_RfdPmt03Sanc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("itemName")
	private String itemName;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refId")
	private String refID;
	
	@JsonProperty("pmtSancAmtOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pmtSancAmtOrderData_id")
	private Refund_PmtSancAmtOrderData pmtSancAmtOrderData;

	@JsonProperty("rfdNoticeData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdNoticeData_id")
	private Refund_RfdNoticeData rfdNoticeData;

}