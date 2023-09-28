package com.backend.Manager_restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_restaurant")
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    private String address;
    @ElementCollection
    @CollectionTable(name = "hours_open", joinColumns = @JoinColumn(name = "restaurant_id"))
    private List<HourOpen> hourOpens;

    // nao tirar = relacionamento de restaurante e produto
    @ManyToMany
    @JoinTable(name = "tb_restaurant_product", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "restaurants")
    private List<Promotion> promotions = new ArrayList<>();

    public Restaurant(){
    }

    public Restaurant(Long id, String name, String img, String address, String hours, List<Product> products, List<Promotion> promotions, List<HourOpen> hourOpens) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.address = address;
        this.products = products;
        this.promotions = promotions;
        this.hourOpens = hourOpens;
    }

    public List<HourOpen> getHourOpens() {
        return hourOpens;
    }

    public void setHourOpens(List<HourOpen> hourOpens) {
        this.hourOpens = hourOpens;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
