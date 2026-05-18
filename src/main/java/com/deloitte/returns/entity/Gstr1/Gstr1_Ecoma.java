package com.deloitte.returns.entity.Gstr1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ecoma", schema = "gstr1")
@Entity
public class Gstr1_Ecoma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private List<Gstr1_B2Ba> b2ba;

	@JsonProperty("b2ca")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private List<Gstr1_B2Ca> b2ca;

	@JsonProperty("urp2ca")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private List<Gstr1_Urp2ca> urp2ca;

	@JsonProperty("urp2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private List<Gstr1_Urp2ba> urp2ba;

}
