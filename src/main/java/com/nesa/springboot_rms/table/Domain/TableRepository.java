package com.nesa.springboot_rms.table.Domain;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    void save(Table table);

    Optional<Table> findById(TableId id);

    List<Table> saveAll(List<Table> tables);
    List<Table> findAll();
}
