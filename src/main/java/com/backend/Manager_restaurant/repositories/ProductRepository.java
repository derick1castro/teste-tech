package com.backend.Manager_restaurant.repositories;

import com.backend.Manager_restaurant.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
