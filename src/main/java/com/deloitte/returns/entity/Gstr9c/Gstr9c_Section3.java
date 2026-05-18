package com.deloitte.returns.entity.Gstr9c;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "section3", schema = "gstr9c")
public class Gstr9c_Section3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("line0")
	@Column(length = 1000)
	private String line0;

	@JsonProperty("sec_3a")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sec_3a_id")
	private Gstr9c_SEC3A sec3A;

	@JsonProperty("sec_3b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sec_3b_id")
	private Gstr9c_SEC3B sec3B;

}
