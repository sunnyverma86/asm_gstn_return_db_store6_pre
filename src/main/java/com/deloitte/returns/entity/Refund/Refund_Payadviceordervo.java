package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "payadviceordervo", schema = "refund")
public class Refund_Payadviceordervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordertype")
	private String ordertype;

	@JsonProperty("remarks")
	@Column(length = 500)
	private String remarks;

	@JsonProperty("rfdsanproorderno")
	private String rfdsanproorderno;

	@JsonProperty("pmtamtdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pmtamtdtl_id")
	private Refund_Pmtamtdtl pmtamtdtl;
	
	
	@JsonProperty("rfdordty")
	private String rfdordty;
	
	@JsonProperty("cwfflag")
	private boolean cwfflag;
	
	
	@JsonProperty("bankdetails")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bankdetails_id")
    private Refund_Bankdetails bankdetails;
 
	@JsonProperty("prepayadvno")
    private String prepayadvno;


}
