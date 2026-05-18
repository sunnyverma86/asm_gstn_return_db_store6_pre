package com.deloitte.returns.entity.type.AdvanceRulingAppeal1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "arapapplndata", schema = "advance_ruling_appeal")
public class Arapa_Arapapplndata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("sdetl")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sdetl_id")
    private Arapa_Sdetl sdetl;

    @JsonProperty("todtls")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todtls_id")
    private Arapa_Todtls todtls;

    @JsonProperty("verdtls")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verdtls_id")
    private Arapa_Verdtls verdtls;

    @JsonProperty("docupdtl")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arapapplndata_id")
    private List<Arapa_Docupdtl> docupdtl;
}

