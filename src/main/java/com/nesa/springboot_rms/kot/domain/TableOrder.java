package com.nesa.springboot_rms.kot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TableOrder {

    private Long id;
    private Long tableNumber;
    private boolean active;
    private List<KOT> kots;

    public static TableOrder startNew(Long tableNumber) {
        return new TableOrder(null, tableNumber, true, new ArrayList<>());
    }

    public void addKOT(List<OrderItem> items) {
        this.kots.add(KOT.createNew(items));
    }

}
