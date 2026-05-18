package com.deloitte.returns.entity.Payment;


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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cin", schema = "payment")


public class Payment_Cin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ner")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cin_id")
	private List<Payment_Ner> ner;
	
	@JsonProperty("otc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cin_id")
	private List<Payment_Otc> otc;
	
	@JsonProperty("epy")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cin_id")
	private List<Payment_Epy> epy;
	
	

}
