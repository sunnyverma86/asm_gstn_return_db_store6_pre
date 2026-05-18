package com.deloitte.returns.entity.type.AdjudicationAppeal;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maindocs", schema = "adjudication_appeal")
public class Appel_Maindocs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dcupdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private Appel_Dcupdtls dcupdtls;

}