package com.deloitte.returns.entity.Gstr6;

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
@Table(name = "latefee", schema = "gstr6")
@Entity
public class Gstr6_Latefee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cLamt")
	private Double cLamt;

	@JsonProperty("debitId")
	private String debitID;

	@JsonProperty("sLamt")
	private Double sLamt;

}