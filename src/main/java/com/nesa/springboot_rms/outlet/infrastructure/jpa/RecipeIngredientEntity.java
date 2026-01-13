package com.nesa.springboot_rms.outlet.infrastructure.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recipe_ingredient")
@Data
public class RecipeIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id") 
    private RecipeEntity recipe;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;

    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit")
    private String unit;
    
}
