package com.deloitte.returns.entity.Gstr9c;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oth",schema = "gstr9c")
public class Gstr9c_Oth {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonProperty("cess")
	private double cess;

	@JsonProperty("cgst")
	private double cgst;

	@JsonProperty("igst")
	private double igst;
	
	@JsonProperty("val")
	private double val;

	@JsonProperty("desc")
	private String descData;

	@JsonProperty("tax_val")
	private Double taxVal;

	@JsonProperty("sgst")
	private double sgst;
}
