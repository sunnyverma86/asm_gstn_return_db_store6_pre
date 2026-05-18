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
@Table(name = "supeco", schema = "gstr1a")
@Entity
public class Gstr1A_Supeco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("paytx")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "supeco_id")
	private List<Gstr1A_Paytx> paytx;
	
	@JsonProperty("clttx")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "supeco_id")
	private List<Gstr1A_Clttx> clttx;

}
