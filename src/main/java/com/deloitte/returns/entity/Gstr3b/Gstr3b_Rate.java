
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "start_dt", "end_dt", "delay", "rate", "interest" })

@Entity
@Data
@Table(name = "rate", schema = "gstr3b")
public class Gstr3b_Rate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("start_dt")
	@Column
	private String startDt;
	@JsonProperty("end_dt")
	@Column
	private String endDt;
	@JsonProperty("delay")
	@Column
	private long delay;
	@JsonProperty("rate")
	@Column
	private double rate;
	@JsonProperty("interest")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "interest_id")
	private Gstr3b_Interest interest;

	private final static long serialVersionUID = 5500733768985017084L;

}
