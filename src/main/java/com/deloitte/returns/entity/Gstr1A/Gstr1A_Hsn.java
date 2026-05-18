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
@Table(name = "hsn", schema = "gstr1a")
@Entity
public class Gstr1A_Hsn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum; // added
	
	
	@JsonProperty("hsn_b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hsn_id")
	private List<Gstr1A_Hsn_b2b> hsn_b2b;

	@JsonProperty("hsn_b2c")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hsn_id")
	private List<Gstr1A_Hsn_b2c> hsn_b2c;
	
	

	@JsonProperty("data")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hsn_id")
	private List<Gstr1A_Datum> data;

	@JsonProperty("flag")
	private String flag;

}
