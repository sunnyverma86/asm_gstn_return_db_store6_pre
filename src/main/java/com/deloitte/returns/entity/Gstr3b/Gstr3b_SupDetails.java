
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "osup_det",
    "osup_zero",
    "osup_nil_exmp",
    "isup_rev",
    "osup_nongst"
})

@Entity
@Data
@Table(name = "sup_details", schema = "gstr3b")
public class Gstr3b_SupDetails implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("osup_det")
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="osup_det_id")
    private Gstr3b_OsupDet osupDet;

    @JsonProperty("osup_zero")
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="osup_zero_id")
    private Gstr3b_OsupZero osupZero;

    @JsonProperty("osup_nil_exmp")
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="osup_nil_exmp_id")
    private Gstr3b_OsupNilExmp osupNilExmp;

    @JsonProperty("isup_rev")
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="isup_rev_id")
    private Gstr3b_IsupRev isupRev;

    @JsonProperty("osup_nongst")
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="osup_non_gst_id")
    private Gstr3b_OsupNongst osupNongst;

     private final static long serialVersionUID = 471999390973150423L;


}
