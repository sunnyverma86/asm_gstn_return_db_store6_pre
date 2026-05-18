
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "iamt", "camt", "samt", "csamt" })

@Entity
@Data
@Table(name = "interest", schema = "gstr3b")
public class Gstr3b_Interest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("iamt")
	@Column
	private double iamt;
	@JsonProperty("camt")
	@Column
	private double camt;
	@JsonProperty("samt")
	@Column
	private double samt;
	@JsonProperty("csamt")
	@Column
	private double csamt;

	private final static long serialVersionUID = 6407210573848480028L;

}
