package com.deloitte.returns.entity.Gstr10;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "a9", schema = "not_used")
public class FileNameGstr10 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;

	private String filePath;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "create_date_time", updatable = false)
	private Instant createDateTime;

	@Column(name = "updated_date_time")
	private Instant updatedDateTime;

	@PrePersist
	protected void onCreate() {
		Instant now = Instant.now();
		this.createDateTime = now;
		this.updatedDateTime = now;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDateTime = Instant.now();
	}
}