
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "b2b", "b2ba", "cdn", "cdna", "isda", "isd", "tds", "tdsa", "tcs", "impg", "impgsez" })

@Entity
@Table(name = "gstr2a", schema = "gstr2a")
@Data
public class Gstr2a implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	public String gstin;
	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_B2b> gstr2aB2B = new ArrayList<Gstr2a_B2b>();

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_B2ba> gstr2aB2Ba = new ArrayList<Gstr2a_B2ba>();

	@JsonProperty("cdn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Cdn> gstr2aCdn = new ArrayList<Gstr2a_Cdn>();

	@JsonProperty("cdna")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Cdna> gstr2aCdna = new ArrayList<Gstr2a_Cdna>();

	@JsonProperty("isda")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Isda> gstr2aIsda = new ArrayList<Gstr2a_Isda>();

	@JsonProperty("isd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Isd> gstr2aIsd = new ArrayList<Gstr2a_Isd>();

	@JsonProperty("tds")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Td> tds = new ArrayList<Gstr2a_Td>();

	@JsonProperty("tdsa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Tdsa> tdsa = new ArrayList<Gstr2a_Tdsa>();

	@JsonProperty("tcs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Tcs> tcs = new ArrayList<Gstr2a_Tcs>();

	@JsonProperty("impg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gtsr2a_Impg> gtsr2aImpg = new ArrayList<Gtsr2a_Impg>();

	@JsonProperty("impgsez")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr2a_id")
	public List<Gstr2a_Impgsez> gstr2aImpgsez = new ArrayList<Gstr2a_Impgsez>();

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

	private final static long serialVersionUID = -1389817726009066028L;

}
