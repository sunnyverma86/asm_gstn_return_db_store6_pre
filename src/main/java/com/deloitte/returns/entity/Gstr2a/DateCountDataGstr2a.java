package com.deloitte.returns.entity.Gstr2a;



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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="date_count_table_gstr2a",schema = "gstr2a")
public class DateCountDataGstr2a {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String date;
    
    @Column
    private String count;

    @Column
    private String isProcessed;

    @Column(name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime;
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;


    @PrePersist
    protected void onCreate(){
        createDateTime = LocalDateTime.now();
        updatedDateTime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }


}
