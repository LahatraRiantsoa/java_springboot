package com.example.myapp.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int stock;

    private String lienImage;

    // Getters and Setters
}