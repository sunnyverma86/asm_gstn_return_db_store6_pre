package com.deloitte.returns.entity.Gstr2b;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "gstr2b", schema = "gstr2b")
@Entity
public class Gstr2b {
	
	//
@Column(name = "return_file_count_primary_id")
private Long returnFileCountPrimaryId;

@Column(name = "return_file_detail_primary_id")
private Long returnFileDetailPrimaryId;
//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fileIndex")
	private Double fileIndex;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("rtnprd")
	private String rtnprd;

	@JsonProperty("totalFiles")
	private Double totalFiles;

	@JsonProperty("r2bdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "r2bdata_id")
	private Gstr2b_R2Bdata r2Bdata;

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

}