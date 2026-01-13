package com.nesa.springboot_rms.outlet.infrastructure.persistence;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.Outlet;
import com.nesa.springboot_rms.outlet.domain.repo.OutletRepository;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.OutletEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.repo.JpaOutletRepo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OutletRepositoryImpl implements OutletRepository {

    private final Mapper<Outlet, OutletEntity> mapper;
    private final JpaOutletRepo jpaOutletRepo;

    @Override
    public void save(Outlet outlet) {
        jpaOutletRepo.save(mapper.toEntity(outlet));
    }

    @Override
    public Collection<Outlet> getAllByHotelId(Long hotelId) {
        Collection<Outlet> collection = jpaOutletRepo.findAllByHotelId(hotelId).stream()
                .map(entity -> mapper.toDomain(entity))
                .collect(Collectors.toList());
        return collection;
    }

    @Override
    public Optional<Outlet> findById(Long id) {
        return jpaOutletRepo.findById(id).map(entity -> mapper.toDomain(entity));
    }

}
