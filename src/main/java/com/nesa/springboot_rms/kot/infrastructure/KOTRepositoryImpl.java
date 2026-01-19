package com.nesa.springboot_rms.kot.infrastructure;

import com.nesa.springboot_rms.kot.domain.KOTRepository;
import com.nesa.springboot_rms.kot.domain.TableOrder;
import com.nesa.springboot_rms.table.Infrastructure.JpaTableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class KOTRepositoryImpl implements KOTRepository {

    private final JpaTableOrderRepository jpaRepo;
    private final KOTMapper mapper;
    private final JpaTableRepository jpaTableRepository;

    @Override
    public boolean tableExists(Long tableId) {
        return jpaTableRepository.existsById(tableId);
    }

    @Override
    public Optional<TableOrder> findActiveByTable(Long tableNumber) {
        return jpaRepo.findByTableNumberAndActiveTrue(tableNumber).map(mapper::toDomain);
    }

    @Override
    public List<TableOrder> findAllActive() {
        return jpaRepo.findByActiveTrue().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(TableOrder order) {
        TableOrderJpaEntity entity = mapper.toEntity(order);
        jpaRepo.saveAndFlush(entity);
    }
}
