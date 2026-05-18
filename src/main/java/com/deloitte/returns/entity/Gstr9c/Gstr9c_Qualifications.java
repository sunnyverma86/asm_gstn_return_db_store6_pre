package com.deloitte.returns.entity.Gstr9c;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qualifications", schema = "gstr9c")
public class Gstr9c_Qualifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("value")
	@Column(length = 2000)
	private String value;

	@JsonProperty("qual_type")
	@Column(length = 2000)
	private String qualType;

}
