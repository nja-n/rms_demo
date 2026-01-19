package com.nesa.springboot_rms.table.Api.dtos;

import lombok.Data;

@Data
public class TableCreateDto {

    Long id;
    int capacity;
    String name;
    double posX;
    double posY;
    Long outletId;
    String status;

}
