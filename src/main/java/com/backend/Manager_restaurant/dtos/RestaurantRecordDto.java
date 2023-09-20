package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.HourOpen;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RestaurantRecordDto(
        @NotBlank @NotNull String name,
        String img,
        @NotBlank @NotNull String address,
        List<HourOpen> hourOpens,
        List<Product> products,
        List<Promotion> promotions) {
}
