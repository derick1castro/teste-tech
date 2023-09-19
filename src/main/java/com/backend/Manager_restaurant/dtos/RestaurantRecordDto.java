package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.HourOpen;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;

import java.util.List;

public record RestaurantRecordDto(String name, String img, String address, List<HourOpen> hourOpens, List<Product> products, List<Promotion> promotions) {
}
