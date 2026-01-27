package com.nesa.springboot_rms.item_demo.domain.Item;

import lombok.Getter;

@Getter
public class Item {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String type;
    private String brand;
    private String image;
}
