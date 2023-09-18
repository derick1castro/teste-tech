package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public record PromotionRecordDto(String description_promotional, BigDecimal price_promotional, String date_promotional, List<Product> products, List<Restaurant> restaurants){
}
