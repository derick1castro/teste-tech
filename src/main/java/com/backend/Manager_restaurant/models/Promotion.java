package com.backend.Manager_restaurant.models;

import com.backend.Manager_restaurant.models.enums.DaysOfWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_promotion")
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description_promotional;
    private BigDecimal price_promotional;

    @ElementCollection
    @CollectionTable(name = "hours_promotion", joinColumns = @JoinColumn(name = "promotion_id"))
    private List<HourPromotion> hourPromotions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "promotion_product",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "promotion_restaurant",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> restaurants = new ArrayList<>();

    public Promotion(){}

    public Promotion(Long id, String description_promotional, BigDecimal price_promotional, List<HourPromotion> hourPromotions, List<Product> products, List<Restaurant> restaurants) {
        this.id = id;
        this.description_promotional = description_promotional;
        this.price_promotional = price_promotional;
        this.hourPromotions = hourPromotions;
        this.products = products;
        this.restaurants = restaurants;
    }

    public List<HourPromotion> getHourPromotions() {
        return hourPromotions;
    }

    public void setHourPromotions(List<HourPromotion> hourPromotions) {
        this.hourPromotions = hourPromotions;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription_promotional() {
        return description_promotional;
    }

    public void setDescription_promotional(String description_promotional) {
        this.description_promotional = description_promotional;
    }

    public BigDecimal getPrice_promotional() {
        return price_promotional;
    }

    public void setPrice_promotional(BigDecimal price_promotional) {
        this.price_promotional = price_promotional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
