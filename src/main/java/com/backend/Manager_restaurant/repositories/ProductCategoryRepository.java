package com.backend.Manager_restaurant.repositories;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
