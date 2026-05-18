package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items", schema = "refund")
public class Refund_Items {

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

	@JsonProperty("rfdAppData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
	private Refund_RfdAppData rfdAppData;//

	@JsonProperty("rfdProOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdProOrderData_id")
	private Refund_RfdProOrderData rfdProOrderData;

	@JsonProperty("rfdSanOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdSanOrderData_id")
	private Refund_RfdSANOrderData rfdSANOrderData;

	@JsonProperty("rfdComOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdComOrderData_id")
	private Refund_RfdCOMOrderData rfdCOMOrderData;

	@JsonProperty("rfdWHeldOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdWHeldOrderData_id")
	private Refund_RfdWHeldOrderData rfdWHeldOrderData;

	@JsonProperty("rfdPmtAdvOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdPmtAdvOrderData_id")
	private Refund_RfdPmtAdvOrderData rfdPmtAdvOrderData;

	@JsonProperty("rfdRejAmtOrderData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdRejAmtOrderData_id")
	private Refund_RfdRejAmtOrderData rfdRejAmtOrderData;

	@JsonProperty("rfdNotice")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Refund_RfdNotice> rfdNotice;//

	@JsonProperty("rfdReply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Refund_RfdReply> rfdReply;

	// new
	@JsonProperty("rfdPmt03Sanc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Refund_RfdPmt03Sanc> rfdPmt03Sanc;
	
	@JsonProperty("rfdPayAdvice")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Refund_RfdPayAdvice> rfdPayAdvice;
	
	@JsonProperty("rfdPmt03")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Refund_RfdPmt03> rfdPmt03;
	

	@JsonProperty("rfdReplyData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdReplyData_id")
	private Refund_RfdReplyData rfdReplyData;
	
	
	 @JsonProperty("rfdWth")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdWth_id")
	private Refund_RfdWth rfdWth;
	 

	



}
