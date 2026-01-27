package com.nesa.springboot_rms.kot.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTableOrderRepository extends JpaRepository<TableOrderJpaEntity,Long> {

    /**
     * Finds the current active session for a specific table.
     * This is the core of your "Running Order" logic.
     */
    Optional<TableOrderJpaEntity> findByTableNumberAndActiveTrue(Long tableNumber);

    /**
     * Finds all active orders across all tables.
     * Used for your "Running Order Window" UI.
     */
    List<TableOrderJpaEntity> findByActiveTrue();
}
