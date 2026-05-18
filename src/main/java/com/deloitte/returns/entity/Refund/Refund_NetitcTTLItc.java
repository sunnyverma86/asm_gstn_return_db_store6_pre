package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "netitc_ttl_itc", schema = "refund")
public class Refund_NetitcTTLItc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cgrfclm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgrfclm_id")
	private Refund_Cgrfclm cgrfclm;

	@JsonProperty("csrfclm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "csrfclm_id")
	private Refund_Csrfclm csrfclm;

	@JsonProperty("igrfclm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igrfclm_id")
	private Refund_Igrfclm igrfclm;

	@JsonProperty("sgrfclm")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgrfclm_id")
	private Refund_Sgrfclm sgrfclm;

}