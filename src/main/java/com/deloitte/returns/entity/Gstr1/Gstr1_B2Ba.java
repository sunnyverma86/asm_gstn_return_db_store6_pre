package com.deloitte.returns.entity.Gstr1;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "b2ba", schema = "gstr1")
@Entity
public class Gstr1_B2Ba {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("ctin")
    private String ctin;
	
	@JsonProperty("cfs")
    private String cfs;
	
	@JsonProperty("stin")
	private String stin;
	
	@JsonProperty("rtin")
	private String rtin;
	
	
	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2ba_id")
	public List<Gstr1_Inv> inv;

 
}
