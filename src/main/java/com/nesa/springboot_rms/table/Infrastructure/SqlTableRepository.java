package com.nesa.springboot_rms.table.Infrastructure;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.table.Domain.Table;
import com.nesa.springboot_rms.table.Domain.TableId;
import com.nesa.springboot_rms.table.Domain.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SqlTableRepository implements TableRepository {
    private final JpaTableRepository jpaRepo;
    private final Mapper<Table, TableJpaEntity> mapper;

    @Override
    public void save(Table table) {
        TableJpaEntity entity = mapper.toEntity(table);
        // entity.setId(table.getId().value());
        // entity.setCapacity(table.getCapacity());
        // entity.setStatus(table.getStatus());
        // entity.setName(table.getName());
        // entity.setPosX(table.getPosX());
        // entity.setPosY(table.getPosY());
        // entity.setOutletId(table.getOutletId().value());
        // entity.setWaiterId(table.getAssignedWaiterId());
        jpaRepo.save(entity);
    }

    @Override
    public Optional<Table> findById(TableId id) {
        return jpaRepo.findById(id.value())
                .map(e -> mapper.toDomain(e));
    }

    @Override
    public List<Table> saveAll(List<Table> tables) {
        List<TableJpaEntity> entities = jpaRepo.saveAll(tables.stream()
                .map(t -> mapper.toEntity(t))
                .collect(Collectors.toList()));
        return entities.stream()
                .map(e -> mapper.toDomain(e))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Table> findAll() {
        return jpaRepo.findAll().stream()
                .map(e -> mapper.toDomain(e))
                .collect(Collectors.toList());
    }
    
}
