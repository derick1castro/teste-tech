package com.backend.Manager_restaurant.dtos;

import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.models.Restaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record ProductRecordDto (
        @NotBlank @NotNull String name,
        @NotNull @PositiveOrZero Double price,
        String img,
        ProductCategory category,
        List<Promotion> promotion){
}
