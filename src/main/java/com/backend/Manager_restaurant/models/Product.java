package com.backend.Manager_restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
@JsonIgnoreProperties("restaurants")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String img;

    //não mexer
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    // não mexer
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Restaurant> restaurant = new ArrayList<>();

    
    @ManyToMany(mappedBy = "products")
    private List<Promotion> promotions = new ArrayList<>();

    public Product(){
    }

    public Product(Long id, String name, Double price, String img, List<Restaurant> restaurant, ProductCategory category, List<Promotion> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.restaurant = restaurant;
        this.category = category;
        this.promotions = promotions;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void add(Product product) {
    }
}
