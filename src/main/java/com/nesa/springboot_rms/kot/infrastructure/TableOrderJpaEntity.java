package com.nesa.springboot_rms.kot.infrastructure;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "table_orders")
@Data
public class TableOrderJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tableNumber;
    private boolean active;

    @OneToMany(
            mappedBy = "tableOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<KOTJpaEntity> kots;


}
