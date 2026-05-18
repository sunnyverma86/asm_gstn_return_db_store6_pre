
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "interest", "interestbreakup" })

@Entity
@Data
@Table(name = "system_interest", schema = "gstr3b")
public class Gstr3b_SystemInterest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("interest")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "interest_id")
	private Gstr3b_Interest interest;

	@JsonProperty("interestbreakup")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "system_interest_id")
	private List<Gstr3b_Interestbreakup> interestbreakup = new ArrayList<Gstr3b_Interestbreakup>();

	private final static long serialVersionUID = 7463098415889713604L;

}
