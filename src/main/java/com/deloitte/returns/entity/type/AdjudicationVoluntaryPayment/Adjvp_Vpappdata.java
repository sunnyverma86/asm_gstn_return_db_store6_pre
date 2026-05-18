package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vpappdata", schema = "adjudication_voluntary_payment")
public class Adjvp_Vpappdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("vp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vp_id")
	private Adjvp_Vp vp;

}
