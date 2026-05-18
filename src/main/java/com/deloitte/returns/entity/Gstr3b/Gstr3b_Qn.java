
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Questionnaire
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor

@Table(name = "qn", schema = "gstr3b")
public class Gstr3b_Qn implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("q1")
	@JsonPropertyDescription("Questionnaire 1")
	@Column
	private String q1;

	@JsonProperty("q2")
	@JsonPropertyDescription("Questionnaire 2")
	@Column
	private String q2;

	@JsonProperty("q3")
	@JsonPropertyDescription("Questionnaire 3")
	@Column
	private String q3;

	@JsonProperty("q4")
	@JsonPropertyDescription("Questionnaire 4")
	@Column
	private String q4;

	@JsonProperty("q5")
	@JsonPropertyDescription("Questionnaire 5")
	@Column
	private String q5;

	@JsonProperty("q6")
	@JsonPropertyDescription("Questionnaire 6")
	@Column
	private String q6;

	@JsonProperty("q7")
	@JsonPropertyDescription("Questionnaire 7")
	@Column
	private String q7;

	private final static long serialVersionUID = -616997188831043353L;

}
