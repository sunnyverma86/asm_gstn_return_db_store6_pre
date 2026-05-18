package com.deloitte.returns.entity.Gstr9a;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table6", schema = "gstr9a")
@Entity
public class Gstr9a_Table6 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("exmp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "exmp_id")
	private Gstr9a_Exmp exmp;

	@JsonProperty("tot")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_id")
	private Gstr9a_Tot tot;

	@JsonProperty("tx_trnovr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private List<Gstr9a_TxTrnovr> txTrnovr;

}