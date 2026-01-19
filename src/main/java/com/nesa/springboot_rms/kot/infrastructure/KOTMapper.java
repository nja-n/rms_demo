package com.nesa.springboot_rms.kot.infrastructure;

import com.nesa.springboot_rms.kot.domain.KOT;
import com.nesa.springboot_rms.kot.domain.OrderItem;
import com.nesa.springboot_rms.kot.domain.TableOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KOTMapper {

    public TableOrderJpaEntity toEntity(TableOrder domain) {
        TableOrderJpaEntity orderEntity = new TableOrderJpaEntity();
        orderEntity.setId(domain.getId());
        orderEntity.setTableNumber(domain.getTableNumber());
        orderEntity.setActive(domain.isActive());

        // Link ALL KOTs (existing ones and the new one) to this Parent
        List<KOTJpaEntity> kotEntities = domain.getKots().stream()
                .map(kot -> {
                    KOTJpaEntity kEntity = mapKotToEntity(kot);
                    kEntity.setTableOrder(orderEntity); // CRITICAL: This maintains the FK
                    return kEntity;
                }).collect(Collectors.toList());

        orderEntity.setKots(kotEntities);
        return orderEntity;
    }

    private KOTJpaEntity mapKotToEntity(KOT domain) {
        KOTJpaEntity entity = new KOTJpaEntity();
        entity.setId(domain.getId());
        entity.setStatus(domain.getStatus());

        // Mapping the items (Value Objects) to Embeddables
        entity.setItems(domain.getItems().stream()
                .map(i -> new OrderItemEmbeddable(i.dishId(), i.itemName(), i.quantity()))
                .collect(Collectors.toList()));

        return entity;
    }

    public TableOrder toDomain(TableOrderJpaEntity entity) {
        if (entity == null) return null;

        return new TableOrder(
                entity.getId(),
                entity.getTableNumber(),
                entity.isActive(),
                entity.getKots().stream().map(this::mapKotToDomain).collect(Collectors.toList())
        );
    }

    private KOT mapKotToDomain(KOTJpaEntity entity) {
        return new KOT(
                entity.getId(),
                entity.getItems().stream()
                        .map(i -> new OrderItem(i.getDishId(), i.getItemName(), i.getQuantity()))
                        .collect(Collectors.toList()),
                entity.getStatus()
        );
    }
}
