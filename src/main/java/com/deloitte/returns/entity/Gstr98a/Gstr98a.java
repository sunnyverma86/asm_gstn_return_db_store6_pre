package com.deloitte.returns.entity.Gstr98a;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gstr9_8a", schema = "gstr9_8a")
@Entity
public class Gstr98a {

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

	@JsonProperty("docid")
	private Double docid;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9_8a_id")
	private List<Gstr9_8a_B2B> b2B;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9_8a_id")
	private List<Gstr9_8a_B2Ba> b2Ba;

	@JsonProperty("cdn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9_8a_id")
	private List<Gstr9_8a_CDN> cdn;

	@JsonProperty("cdna")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9_8a_id")
	private List<Gstr9_8a_Cdna> cdna;

}
