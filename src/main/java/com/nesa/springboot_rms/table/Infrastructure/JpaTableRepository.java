package com.nesa.springboot_rms.table.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTableRepository extends JpaRepository<TableJpaEntity, Long> {

    // This allows the KOT service to verify if the table is real
    boolean existsByName(String name);
}
