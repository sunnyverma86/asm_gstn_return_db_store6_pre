package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "total", schema = "adjudication_unregistered_persons")
public class Adjur_Total {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fee")
	private Long fee;

	@JsonProperty("intr")
	private Long intr;

	@JsonProperty("oth")
	private Long oth;

	@JsonProperty("pen")
	private Long pen;

	@JsonProperty("tx")
	private Long tx;

}
