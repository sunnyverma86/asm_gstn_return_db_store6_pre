package com.deloitte.returns.entity.Gstr1A;

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
@Table(name = "cdnra", schema = "gstr1a")
public class Gstr1A_Cdnra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("cfs")
	private String cfs;

	@JsonProperty("nt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnra_id")
	private List<Gstr1A_Nt> nt;

}
