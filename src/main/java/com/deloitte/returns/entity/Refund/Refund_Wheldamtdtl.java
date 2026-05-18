package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wheldamtdtl", schema = "refund")
public class Refund_Wheldamtdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("alwdamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "alwdamt_id")
	private Refund_Alwdamt alwdamt;

	@JsonProperty("sancamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sancamt_id")
	private Refund_Sancamt sancamt;

	@JsonProperty("wthldamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wthldamt_id")
	private Refund_Wthldamt wthldamt;

}