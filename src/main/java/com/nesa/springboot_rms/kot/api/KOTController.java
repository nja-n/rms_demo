package com.nesa.springboot_rms.kot.api;

import com.nesa.springboot_rms.kot.application.KOTService;
import com.nesa.springboot_rms.kot.domain.OrderItem;
import com.nesa.springboot_rms.kot.domain.TableOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kot")
public class KOTController {


    @Autowired
    private KOTService kotService;

    @PostMapping("/{tableNumber}")
    public ResponseEntity<?> createKOT(@PathVariable Long tableNumber, @RequestBody List<OrderItem> items) {
        try {
            kotService.processOrder(tableNumber, items);
            return ResponseEntity.ok("KOT generated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/running")
    public List<TableOrder> getRunning() {
        return kotService.getAllRunningOrders();
    }

}