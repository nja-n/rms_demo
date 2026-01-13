package com.nesa.springboot_rms.outlet.application;

import org.springframework.stereotype.Service;

import com.nesa.springboot_rms.outlet.domain.Hotel;
import com.nesa.springboot_rms.outlet.domain.extention.HotelId;
import com.nesa.springboot_rms.outlet.domain.repo.HotelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelApplicationService {
    private final HotelRepository hotelRepository;
    public void createHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }
    public Hotel getHotel(Long id) {
        return hotelRepository.findById(new HotelId(id)).orElse(null);
    }
    
}
