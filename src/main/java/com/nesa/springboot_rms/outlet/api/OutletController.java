package com.nesa.springboot_rms.outlet.api;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.outlet.application.OutletApplicationService;
import com.nesa.springboot_rms.outlet.domain.Outlet;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/outlet")
@RequiredArgsConstructor
public class OutletController {

    private final OutletApplicationService outletService;
    private final HotelController hotelController;

    protected Long hardCodedOutletId = 1L;
    
    @PostMapping
    public ResponseEntity<String> saveOutlet(@RequestBody Outlet outlet) {
        outletService.createOutlet(outlet);
        return ResponseEntity.status(HttpStatus.CREATED).body("Outlet created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Outlet>> getAllOutlets() {
        Long hotelId = hotelController.hardCodedHotelId;
        return ResponseEntity.ok(outletService.getAllOutlets(hotelId));
    }

    @PatchMapping("/{id}/toggleActive")
    public String toggleActive(@PathVariable Long id) {
        outletService.toggleActive(id);
        return "Outlet toggled successfully";
    }
    
    
    
}
