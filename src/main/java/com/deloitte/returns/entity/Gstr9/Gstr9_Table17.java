package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table17", schema = "gstr9")
@Entity
public class Gstr9_Table17 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table17_id")
	private List<Gstr9_Items> items;

}