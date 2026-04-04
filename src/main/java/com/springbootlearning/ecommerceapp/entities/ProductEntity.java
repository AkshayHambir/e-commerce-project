package com.springbootlearning.ecommerceapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;

    private String description;

    private int quantity;

    private double price;

    private double specialPrice;

    private double discount;

    @ManyToMany
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private String image;
}
