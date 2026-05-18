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
@Table(name = "rfdReply", schema = "refund")
public class Refund_RfdReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("rfdReplyData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdReplyData_id")
	private Refund_RfdReplyData rfdReplyData;

	@JsonProperty("rfdRejAmtOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdRejAmtOrderData_id")
	private Refund_RfdRejAmtOrderData rfdRejAmtOrderData;

	@JsonProperty("itemName")
	private String itemNames;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("refId")
	private String refId;
	
	

}