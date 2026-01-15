package com.nesa.springboot_rms.table.Domain;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

public class Table {

    private final TableId id;
    private final int capacity;
    private TableStatus status;
    private String name;
    private double posX;
    private double posY;
    private OutletId outletId;

    // Constructor for creating new tables
    public Table(
        TableId id, 
        int capacity, 
        TableStatus status,
        String name, double posX,
            double posY, OutletId outletId) {
        this.id = id;
        this.capacity = capacity;
        this.status = status;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.outletId = outletId;
    }

    // // Constructor for reconstitution from persistence
    // public Table(TableId id, int capacity, TableStatus status) {
    // this.id = id;
    // this.capacity = capacity;
    // this.status = status;

    // }

    public void release() {
        this.status = TableStatus.FREE;

    }

    public TableId getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public OutletId getOutletId() {
        return outletId;
    }

}
