package com.backend.Manager_restaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCategoryRecordDto(
        @NotBlank @NotNull String name
        ){}
