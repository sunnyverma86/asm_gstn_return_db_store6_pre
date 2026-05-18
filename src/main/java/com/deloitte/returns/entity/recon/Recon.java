package com.deloitte.returns.entity.recon;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recon", schema = "recon")
public class Recon {

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

	@JsonProperty("all_prcsd")
	private String allPrcsd;

	@JsonProperty("flg_dt")
	private String flgDt;

	@JsonProperty("prcsd_for_flg_dt")
	private Long prcsdForFlgDt;

	@JsonProperty("rtn_typ")
	private String rtnTyp;

	@JsonProperty("total_filed")
	private Long totalFiled;

	@JsonProperty("prcsing_dtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recon_id")
	private List<Recon_PrcsingDtl> prcsingDtls;

}
