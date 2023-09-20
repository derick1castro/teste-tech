package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.HourOpen;
import com.backend.Manager_restaurant.models.HourPromotion;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record PromotionRecordDto(
        @NotBlank @NotNull String description_promotional,
        @NotNull @PositiveOrZero BigDecimal price_promotional,
        List<HourPromotion> hourPromotions,
        List<Product> products, List<Restaurant> restaurants){
}
