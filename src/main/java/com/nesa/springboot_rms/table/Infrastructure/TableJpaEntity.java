package com.nesa.springboot_rms.table.Infrastructure;

import java.util.UUID;

import com.nesa.springboot_rms.table.Domain.TableStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_tables") // Maps to database table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private TableStatus status;
    private String name;
    private double posX;
    private double posY;
    private long outletId;

}