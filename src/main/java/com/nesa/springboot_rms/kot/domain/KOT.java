package com.nesa.springboot_rms.kot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KOT {

    private Long id;
    private List<OrderItem> items;
    private KOTStatus status;

    public static KOT createNew(List<OrderItem> items) {
        return new KOT(null, items, KOTStatus.PENDING);
    }
}
