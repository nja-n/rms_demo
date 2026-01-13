package com.nesa.springboot_rms.outlet.application;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.nesa.springboot_rms.outlet.domain.Outlet;
import com.nesa.springboot_rms.outlet.domain.repo.OutletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutletApplicationService {
    private final OutletRepository outletRepository;

    public void createOutlet(Outlet outlet) {
        outletRepository.save(outlet);
    }

    public Collection<Outlet> getAllOutlets(Long hotelId) {
        return outletRepository.getAllByHotelId(hotelId);
    }

    public void toggleActive(Long id) {
        Outlet outlet = outletRepository.findById(id).orElseThrow(() -> new RuntimeException("Outlet not found"));
        outlet.toggleActive();
        outletRepository.save(outlet);
    }

}
