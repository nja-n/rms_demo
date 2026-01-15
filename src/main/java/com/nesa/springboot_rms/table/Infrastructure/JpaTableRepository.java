package com.nesa.springboot_rms.table.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTableRepository extends JpaRepository<TableJpaEntity, Long> {
}
