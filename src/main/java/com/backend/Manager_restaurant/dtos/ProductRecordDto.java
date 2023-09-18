package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.models.Restaurant;

import java.util.List;

public record ProductRecordDto (String name, Double price, String img, ProductCategory category, List<Promotion> promotion){
}
