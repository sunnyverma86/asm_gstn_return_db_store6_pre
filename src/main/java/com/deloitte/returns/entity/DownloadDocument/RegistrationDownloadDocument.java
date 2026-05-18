package com.deloitte.returns.entity.DownloadDocument;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "registration_download_document", schema = "download_document")
public class RegistrationDownloadDocument {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "gstin_number")
	    private String gstinNumber;
	    
//	    @Lob
//	    @Column(name = "data", columnDefinition = "bytea")
//	    private byte[] data;


	    @Lob
	    @Basic(fetch = FetchType.EAGER)
	    @Column(name = "data")
	    private byte[] data;
	  

	    @Column(name = "content_type")
	    private String contentType;

	    @PrePersist
	    public void prePersist() {
	        this.createDateTime = LocalDateTime.now();
	        this.updatedDateTime = LocalDateTime.now();
	    }

	    @PreUpdate
	    public void preUpdate() {
	        this.updatedDateTime = LocalDateTime.now();
	    }


	    @Column(name = "create_date_time")
	    private LocalDateTime createDateTime;

	    @Column(name = "updated_date_time")
	    private LocalDateTime updatedDateTime;
}
