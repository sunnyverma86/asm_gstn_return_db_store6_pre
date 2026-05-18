package com.deloitte.returns.entity.Gstr11;

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
@Table(name = "cdnr", schema = "gstr11")
@Entity
public class Gstr11_Cdnr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("nt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnr_id")
	private List<Gstr11_NT> nt;

}