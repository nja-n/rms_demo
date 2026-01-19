package com.nesa.springboot_rms.kot.domain;

import java.util.List;
import java.util.Optional;

public interface KOTRepository {

    boolean tableExists(Long tableId);
    Optional<TableOrder> findActiveByTable(Long tableNumber);
    List<TableOrder> findAllActive();
    void save(TableOrder order);}
