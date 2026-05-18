package com.deloitte.returns.entity;

import java.time.LocalDateTime;
import java.util.List;

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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "return_count_crn", schema = "crn_details")
public class ReturnCountCrn {
//	public String statusCd;
//	public String data;
//	public String rek;
//	public String hmac;
	// public List<DayCount> daycnt; // Add this field to match the JSON structure

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long crncnt;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "return_count_crn_id")
	private List<Crnlist> crnlist;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "return_count_crn_id")
	private List<DayCount> daycnt;

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
