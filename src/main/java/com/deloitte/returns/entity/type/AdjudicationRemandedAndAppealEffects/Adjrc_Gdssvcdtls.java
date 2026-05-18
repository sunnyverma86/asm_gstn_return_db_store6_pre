package com.deloitte.returns.entity.type.AdjudicationRemandedAndAppealEffects;

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
@Entity
@Table(name = "gdssvcdtls", schema = "adjudication_remanded_and_appeal_effects")
public class Adjrc_Gdssvcdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bzgddtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private List<Adjrc_Bzgddtls> bzgddtls;

	@JsonProperty("bzsdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private List<Adjrc_Bzsdtls> bzsdtls;

}