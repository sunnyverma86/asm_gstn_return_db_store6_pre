package com.deloitte.returns.entity.Refund;

import java.time.LocalDateTime;

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
@Table(name = "file_names_refund", schema = "refund_details")
public class FileDetailsRefund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String fileName;

	@Column
	private String filePath;

	@Column
	private String type;

	@Column
	private String startDateTime;

	@Column
	private Boolean isProcessed = false;
	
	@Column
	private String foundData;

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
