package com.nesa.springboot_rms.outlet.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.outlet.application.HotelApplicationService;
import com.nesa.springboot_rms.outlet.domain.Hotel;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelApplicationService hotelApplicationService;

    protected Long hardCodedHotelId = 2l;
    
    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        hotelApplicationService.createHotel(hotel);
        return ResponseEntity.ok(hotel);
    }

    // update with user login info
    @GetMapping
    public ResponseEntity<Hotel> getHotel() {
        return ResponseEntity.ok(hotelApplicationService.getHotel(hardCodedHotelId));
    }
    
}
