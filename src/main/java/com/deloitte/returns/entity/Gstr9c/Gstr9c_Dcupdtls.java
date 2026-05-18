package com.deloitte.returns.entity.Gstr9c;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dcupdtls", schema = "gstr9c")
public class Gstr9c_Dcupdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("balance_sheet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private List<Gstr9c_BalanceSheet> balanceSheet;

	@JsonProperty("otherdoc1")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private List<Gstr9c_Otherdoc1> otherdoc1;

	@JsonProperty("otherdoc2")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private List<Gstr9c_Otherdoc2> otherdoc2;

	@JsonProperty("profitloss")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dcupdtls_id")
	private List<Gstr9c_Profitloss> profitloss;

}
