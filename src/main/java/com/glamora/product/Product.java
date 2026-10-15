package com.glamora.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotBlank private String category;
    @NotNull @DecimalMin("0.0") private BigDecimal price;
    @Column(length = 1000) private String description;
    @Column(length = 1000) private String imageUrl;
    private String badge;
    private Integer stock = 20;

    public Product() {}
    public Product(String name, String category, BigDecimal price, String description, String imageUrl, String badge, Integer stock) {
        this.name=name; this.category=category; this.price=price; this.description=description;
        this.imageUrl=imageUrl; this.badge=badge; this.stock=stock;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal price){this.price=price;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
    public String getBadge(){return badge;} public void setBadge(String badge){this.badge=badge;}
    public Integer getStock(){return stock;} public void setStock(Integer stock){this.stock=stock;}
}
