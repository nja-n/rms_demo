package com.nesa.springboot_rms.kitchen.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientEntity {

    @Id
    @Column(name = "id")
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String thumbnail;

    private String type;

    private Long outletId;

    // getters & setters
}
