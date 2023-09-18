package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.repositories.ProductRepository;
import com.backend.Manager_restaurant.repositories.PromotionRepository;
import com.backend.Manager_restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
//
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Autowired
    private ProductRepository productRepository;

    public Promotion save(Promotion promotion){


         promotionRepository.save(promotion);

        return promotion;

    }

    public List<Promotion> findAll(){

        return promotionRepository.findAll();
    }

    public Promotion findById(Long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return promotion.get();
    }
    public void delete(Long id){
        try {
            promotionRepository.deleteById(id);
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

}
