package com.nesa.springboot_rms.table.Infrastructure;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.table.Domain.Table;
import com.nesa.springboot_rms.table.Domain.TableId;

@Component
public class TableRepositoryMapper implements Mapper<Table, TableJpaEntity> {
    public Table toDomain(TableJpaEntity entity) {
        return new Table(
                new TableId(entity.getId()),
                entity.getCapacity(),
                entity.getStatus(),
                entity.getName(),
                entity.getPosX(),
                entity.getPosY(),
                new OutletId(entity.getOutletId())

        );
    }

    public TableJpaEntity toEntity(Table domain) {
        TableJpaEntity entity = new TableJpaEntity();
        if(domain.getId() != null){
            entity.setId(domain.getId().value());
        }
        entity.setCapacity(domain.getCapacity());
        entity.setStatus(domain.getStatus());
        entity.setName(domain.getName());
        entity.setPosX(domain.getPosX());
        entity.setPosY(domain.getPosY());
        entity.setOutletId(domain.getOutletId().getValue());
        return entity;
    }
}
