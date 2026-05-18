package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "prsnlhrng", schema = "refund")
public class Refund_Prsnlhrng {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dateofhearing")
	private String dateofhearing;

	@JsonProperty("requestedtime")
	private String requestedtime;

	@JsonProperty("timeofhearing")
	private String timeofhearing;

	@JsonProperty("requesteddate")
	private String requesteddate;

}
