package com.backend.Manager_restaurant.repositories;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
