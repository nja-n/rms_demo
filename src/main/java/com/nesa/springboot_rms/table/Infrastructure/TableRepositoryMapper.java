package com.nesa.springboot_rms.table.Infrastructure;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.table.Domain.Table;
import com.nesa.springboot_rms.table.Domain.TableId;

@Component
public class TableRepositoryMapper implements Mapper<Table, TableJpaEntity> {
    public TableJpaEntity toEntity(Table domain) {
        TableJpaEntity entity = new TableJpaEntity();
        entity.setId(domain.getId());
        entity.setCapacity(domain.getCapacity());
        entity.setName(domain.getName());
        entity.setPosX(domain.getPosX());
        entity.setPosY(domain.getPosY());
        entity.setOutletId(domain.getOutletId());
        entity.setStatus(domain.getStatus());
        return entity;
    }

    public Table toDomain(TableJpaEntity entity) {
        return new Table(
                entity.getId(),
                entity.getCapacity(),
                entity.getName(),
                entity.getPosX(),
                entity.getPosY(),
                entity.getOutletId(),
                entity.getStatus()
        );
    }
}
