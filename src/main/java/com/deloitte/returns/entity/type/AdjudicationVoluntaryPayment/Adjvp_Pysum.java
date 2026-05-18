package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@Entity
@Table(name = "pysum", schema = "adjudication_voluntary_payment")
public class Adjvp_Pysum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cs")
	private String cs;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("me")
	private String me;

	@JsonProperty("othsec")
	private String othsec;

	@JsonProperty("paymentdate")
	private String paymentdate;

	@JsonProperty("prn")
	private String prn;

	@JsonProperty("rsn")
	private String rsn;

	@JsonProperty("scndt")
	private String scndt;

	@JsonProperty("scnno")
	private String scnno;

//	@JsonProperty("sec")
//	private String sec;
	
//	@JsonProperty("sec")
//	@ElementCollection
//	private List<String> sec;
	
	
	@JsonProperty("sec")
	@JsonDeserialize(using = Adjvp_SecDeserializer.class)
	@ElementCollection
	private List<String> sec;
	
	@JsonProperty("typeOfDoc")
    private String typeOfDoc;
	
	@JsonProperty("paymentjson")
	@Column(length = 2500) 
	private String paymentjson;
	
	@JsonProperty("arn")
	private String arn;

	@JsonProperty("ovtp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ovtp_id")
	private Adjvp_Ovtp ovtp;

	@JsonProperty("lbltydtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lbltydtls_id")
	private Adjvp_Lbltydtls lbltydtls;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjvp_Decdtls decdtls;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pysum_id")
	private List<Adjvp_Dcupdtls> dcupdtls;

}