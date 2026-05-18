package com.deloitte.returns.entity.Gstr7;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_paid",schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pd_by_cash"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_TaxPaid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("pd_by_cash")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_paid_id")
    public List<Gstr7_PdByCash> pdByCash;
    
    
    @JsonProperty("pd_by_nls")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_paid_id")
    private List<Gstr7_PdByNLS> pdByNLS;

}
