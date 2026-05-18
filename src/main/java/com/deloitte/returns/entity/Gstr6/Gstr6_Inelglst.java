package com.deloitte.returns.entity.Gstr6;

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
@Table(name = "inelglst", schema = "gstr6")
@Entity
public class Gstr6_Inelglst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cpty")
	private String cpty;

	@JsonProperty("rcpty")
	private String rcpty;

	@JsonProperty("rstatecd")
	private String rstatecd;

	@JsonProperty("statecd")
	private String statecd;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("doclst")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inelglst_id")
	private List<Gstr6_Doclst> doclst;

}