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
@Table(name = "scninadmrsnList", schema = "refund")
public class Refund_ScninadmrsnList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("amt")
	private long amt;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("chkStatus")
	private boolean chkStatus;

	@JsonProperty("name")
	private String name;

}
