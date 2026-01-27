package com.nesa.springboot_rms.kot.infrastructure;

import com.nesa.springboot_rms.kot.domain.KOTStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "kots")
@Data

public class KOTJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private KOTStatus status;

    @ElementCollection
    @CollectionTable(
            name = "kot_items",
            joinColumns = @JoinColumn(name = "kot_id", nullable = false)
    )
    private List<OrderItemEmbeddable> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_order_id")
    private TableOrderJpaEntity tableOrder;
}
