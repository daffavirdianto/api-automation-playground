package com.automation.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private String price;
    private String brand;
    private Category category;
}
