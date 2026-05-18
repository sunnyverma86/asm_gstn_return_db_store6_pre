
package com.deloitte.returns.entity.registds;

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
@Entity
@Table(name = "bkacdtls", schema = "registds")
public class TdsTcs_Bkacdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eid")
	public String eid;

	@JsonProperty("acno")
	public String acno;

	@JsonProperty("acty")
	public String acty;

	@JsonProperty("acty_oth")
	public String actyOth;

	@JsonProperty("ifsc")
	public String ifsc;

	@JsonProperty("bknm")
	public String bknm;

	@JsonProperty("brad")
	public String brad;

	@JsonProperty("validSource")
	public String validSource;

	@JsonProperty("validStatus")
	public String validStatus;

	@JsonProperty("validPrimAccHoldName")
	public String validPrimAccHoldName;

	@JsonProperty("validPrimAccHoldPan")
	public String validPrimAccHoldPan;

	@JsonProperty("bankStatus")
	public String bankStatus;

	@JsonProperty("bankRemarks")
	public String bankRemarks;

	@JsonProperty("acctVerifiedDt")
	public String acctVerifiedDt; // Object

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bkacdtls_id")
	public List<TdsTcs_Dcupdtl> dcupdtls;

}
