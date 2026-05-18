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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statutoryorderdetails", schema = "refund")
public class Refund_Statutoryorderdetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("orderno")
	private String orderno;
	
	@JsonProperty("demandid")
    private String demandid;
	
	@JsonProperty("orderissuingauth")
    private String orderissuingauth;
	
	@JsonProperty("paymentreferencenumbers")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "statutoryorderdetails_id")
    private List<Refund_Paymentreferencenumber> paymentreferencenumbers;
	
	@JsonProperty("orderdate")
    private String orderdate;
	
	@JsonProperty("otherorder")
    private String otherorder;
	
	@JsonProperty("typeoforder")
    private String typeoforder;

}
