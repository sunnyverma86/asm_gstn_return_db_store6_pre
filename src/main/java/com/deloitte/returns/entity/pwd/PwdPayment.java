package com.deloitte.returns.entity.pwd;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "pwd", schema = "pwd")   
@Entity
public class PwdPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("OfficeName")
    @Column(name = "office_name",length = 5000)
    private String officeName;

    @JsonProperty("Finyear")
    @Column(name = "finyear")
    private String finyear;

    @JsonProperty("Month")
    @Column(name = "month")
    private String month;

    @JsonProperty("Vrno")
    @Column(name = "vrno")
    private String vrno;

    @JsonProperty("VrDT")
    @Column(name = "vrdt")
    private String vrDT;

    @JsonProperty("WorkName")
    @Column(name = "work_name",length = 5000)
    private String workName;

    @JsonProperty("GSTIN")
    @Column(name = "gstin")
    private String gstin;

    @JsonProperty("PANNo")
    @Column(name = "panno")
    private String panno;

    @JsonProperty("Advances")
    @Column(name = "advances")
    private String advances;

    @JsonProperty("GrossAmt")
    @Column(name = "gross_amt")
    private String grossAmt;

    @JsonProperty("GST_Amt")
    @Column(name = "gst_amt")
    private String gst_Amt;

    @JsonProperty("GST_TDS")
    @Column(name = "gst_tds")
    private String gst_TDS;

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
