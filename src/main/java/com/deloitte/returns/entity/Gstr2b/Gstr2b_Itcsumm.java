package com.deloitte.returns.entity.Gstr2b;

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
@Table(name = "itcsumm", schema = "gstr2b")
@Entity
public class Gstr2b_Itcsumm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itcavl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itcavl_id")
	private Gstr2b_Itcavl itcavl;

	@JsonProperty("itcunavl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itcunavl_id")
	private Gstr2b_Itcunavl itcunavl;
	
	@JsonProperty("itcRejected")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itcRejected_id")
	 private Gstr2b_ItcRejected itcRejected;

	    

}