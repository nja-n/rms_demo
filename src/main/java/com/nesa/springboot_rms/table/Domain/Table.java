package com.nesa.springboot_rms.table.Domain;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Table {

    private Long id;
    private int capacity;
    private String name;
    private double posX;
    private double posY;
    private Long outletId;
    private TableStatus status;

    // Logic: Tables are created as AVAILABLE by default if not specified
    public static Table createNew(int capacity, String name, double x, double y, Long outletId, String status) {
        return new Table(
                null,
                capacity,
                name,
                x,
                y,
                outletId,
                status != null ? TableStatus.fromString(status) : TableStatus.AVAILABLE
        );
    }
}
