package com.deloitte.returns.entity.log;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ledger_initial_json", schema = "log")
public class LedgerInitialJson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Long id;

    @Column(name = "\"RegularTaxpayer_Id\"")
    private Long regularTaxpayerId;

    @Column(name = "gstin", length = 20, nullable = false)
    private String gstin;

    @Column(name = "ledger_typ", length = 20)
    private String ledgerTyp;

    @Column(name = "fr_dt")
    private LocalDate frDt;

    @Column(name = "to_dt")
    private LocalDate toDt;

    @Column(name = "fr_dt_day")
    private Integer frDtDay;
    
    @Column(name = "apprvdt")
	private LocalDate apprvdt;

    @Column(name = "fr_dt_month")
    private Integer frDtMonth;

    @Column(name = "fr_dt_year")
    private Integer frDtYear;

    @Column(name = "to_dt_day")
    private Integer toDtDay;

    @Column(name = "to_dt_month")
    private Integer toDtMonth;

    @Column(name = "to_dt_year")
    private Integer toDtYear;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "msg", columnDefinition = "TEXT")
    private String msg;

//    @Column(name = "jsondata", columnDefinition = "jsonb")
//    @JdbcTypeCode(SqlTypes.JSON)
//    private String jsondata;
    
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "jsondata")
	private JsonNode jsondata;

    @Column(name = "is_insert")
    private Boolean isInsert;

    @Column(name = "insert_date")
    private LocalDateTime insertDate;

    @Column(name = "\"ledgerinsert_Id\"")
    private Long ledgerinsertId;

}