package com.deloitte.returns.entity.Gstr6;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gstr6", schema = "gstr6")
@Entity
public class Gstr6 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("isd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isd_id")
	private Gstr6_Isd isd;

	@JsonProperty("isda")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isda_id")
	private Gstr6_Isda isda;

	@JsonProperty("itc_bal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_bal_id")
	private Gstr6_ItcBAL itcBAL;

	@JsonProperty("latefee_det")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "latefee_det_id")
	private Gstr6_LatefeeDet latefeeDet;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr6_id")
	private List<Gstr6_B2B> b2B;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr6_id")
	private List<Gstr6_B2Ba> b2Ba;

	@JsonProperty("cdn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr6_id")
	private List<Gstr6_CDN> cdn;

	@JsonProperty("cdna")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr6_id")
	private List<Gstr6_Cdna> cdna;

}