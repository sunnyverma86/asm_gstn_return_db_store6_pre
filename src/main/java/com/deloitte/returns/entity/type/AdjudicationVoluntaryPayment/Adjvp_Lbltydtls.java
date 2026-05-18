package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lbltydtls", schema = "adjudication_voluntary_payment")
public class Adjvp_Lbltydtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 @JsonProperty("summ")
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "summ_id")
	 private Adjvp_Summ summ;
	
	@JsonProperty("act")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lbltydtls_id")
	private List<Adjvp_Act> act;

}
