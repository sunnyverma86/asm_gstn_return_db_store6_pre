package com.deloitte.returns.entity.EwayBill;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemList", schema = "eway_live_eway_bill_new")
public class EwayBill_ItemList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ItemNo")
	private long itemNo;

	@JsonProperty("ProdNam")
	private String prodNam;

	@JsonProperty("HsnCod")
	private long hsnCod;

	@JsonProperty("Qty")
	private double qty;

	@JsonProperty("QtyUqc")
	private String qtyUqc;

	@JsonProperty("CGSTRt")
	private double cgstRt;

	@JsonProperty("SGSTRt")
	private double sgstRt;

	@JsonProperty("IGSTRt")
	private double igstRt;

	@JsonProperty("CessRt")
	private long cessRt;

	@JsonProperty("CessAdvol")
	private long cessAdvol;

	@JsonProperty("CessNonAdvol")
	private long cessNonAdvol;

	@JsonProperty("AssAmt")
	private double assAmt;

	// New columns to store EwbNos and ewbNo from the EwayBill_Ewb table
	@Column(name = "Ewb_nos")
	private Long ewbNos;

	@Column(name = "Ewb_no")
	private Long ewbNo;

}