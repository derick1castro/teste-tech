package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;

import java.util.List;

public record RestaurantRecordDto(String name, String img, String address, String hours, List<Product> products, List<Promotion> promotions) {
}
