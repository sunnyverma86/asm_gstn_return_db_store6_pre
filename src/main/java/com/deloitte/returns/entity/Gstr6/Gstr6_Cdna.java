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
@Table(name = "cdna", schema = "gstr6")
@Entity
public class Gstr6_Cdna {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cfs")
	private String cfs;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("nt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdna_id")
	private List<Gstr6_NT> nt;
}