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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "add_liab", schema = "gstr9c")
public class Gstr9c_AddLiab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("date")
	private String date;

	@JsonProperty("mem_no")
	private String memNo;

	@JsonProperty("pan_no")
	private String panNo;

	@JsonProperty("place")
	private String place;

	@JsonProperty("signatory")
	private String signatory;

	@JsonProperty("audit_addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "audit_addr_id")
	private Gstr9c_AuditAddr auditAddr;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_liab_id")
	private List<Gstr9c_Rate> taxPay;

}
