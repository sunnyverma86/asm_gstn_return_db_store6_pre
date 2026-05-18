package com.deloitte.returns.entity.Refund;

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
@Table(name = "rfdReplyData", schema = "refund")
public class Refund_RfdReplyData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("replydt")
	private String replydt;

	@JsonProperty("replyId")
	private String replyID;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Refund_Decdtls decdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "maindocs_id")
	private List<Refund_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdReplyData_id")
	private List<Refund_Suppdocs> suppdocs;

	@JsonProperty("tyreply")
	private String tyreply;

	@JsonProperty("undertakingAmtDtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "undertakingAmtDtl_id")
	private Refund_UndertakingAmtDtl undertakingAmtDtl;

	@JsonProperty("rplyextnreq")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rplyextnreq_id")
	private Refund_Rplyextnreq rplyextnreq;

	@JsonProperty("prsnlhrng")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prsnlhrng_id")
	private Refund_Prsnlhrng prsnlhrng;

}