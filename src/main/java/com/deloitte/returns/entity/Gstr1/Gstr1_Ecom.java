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
@Table(name = "ecom", schema = "gstr1")
@Entity
public class Gstr1_Ecom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("b2c")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private List<Gstr1_B2C> b2c;

	@JsonProperty("urp2c")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private List<Gstr1_Urp2c> urp2c;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private List<Gstr1_B2B> b2b;

	@JsonProperty("urp2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private List<Gstr1_Urp2b> urp2b;

}
