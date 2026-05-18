package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rfdWth", schema = "refund")
public class Refund_RfdWth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ttlRfdAmt")
	private String ttlRfdAmt;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdWth_id")
	private List<Refund_Suppdocs> suppdocs;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("rfdSubDt")
	private String rfdSubDt;

	@JsonProperty("legalName")
	private String legalName;

	@JsonProperty("maindocs")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maindocs_id")
	private Refund_Maindocs maindocs;

	@JsonProperty("toRetPrd")
	private String toRetPrd;

	@JsonProperty("withdrawRsn")
	@Column(length = 500)
	private String withdrawRsn;

	@JsonProperty("fromRetPrd")
	private String fromRetPrd;

	@JsonProperty("refundRsn")
	private String refundRsn;

	@JsonProperty("refId")
	private String refID;

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("status")
	private String status;

}
