package com.nesa.springboot_rms.kot.application;

import com.nesa.springboot_rms.kot.domain.KOTRepository;
import com.nesa.springboot_rms.kot.domain.OrderItem;
import com.nesa.springboot_rms.kot.domain.TableOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KOTService {

    private final KOTRepository kotRepository;


    @Transactional
    public void processOrder(Long tableNumber, List<OrderItem> items) {

        if (!kotRepository.tableExists(tableNumber)) {
            throw new IllegalArgumentException("Table number " + tableNumber + " does not exist in the restaurant.");
        }
        TableOrder order = kotRepository.findActiveByTable(tableNumber)
                .orElseGet(() -> TableOrder.startNew(tableNumber));

        order.addKOT(items);

        kotRepository.save(order);
    }

    public List<TableOrder> getAllRunningOrders() {
        return kotRepository.findAllActive();
    }

}
