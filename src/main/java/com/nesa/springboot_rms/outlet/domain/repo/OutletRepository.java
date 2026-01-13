package com.nesa.springboot_rms.outlet.domain.repo;

import java.util.Collection;
import java.util.Optional;

import com.nesa.springboot_rms.outlet.domain.Outlet;

public interface OutletRepository {
    
    public void save(Outlet outlet);

    public Collection<Outlet> getAllByHotelId(Long hotelId);

    public Optional<Outlet> findById(Long id);
}
