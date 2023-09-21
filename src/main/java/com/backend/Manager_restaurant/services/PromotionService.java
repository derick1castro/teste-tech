package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.exceptions.PromotionNotFoundException;
import com.backend.Manager_restaurant.exceptions.WrongValueException;
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

        if (isNumeric(promotion.getDescription_promotional())) {
            throw new PromotionNotFoundException("Please set a valid value");
        }

         return promotionRepository.save(promotion);
    }

    private boolean isNumeric(String str) {

        try {
            Double.parseDouble(str); // Tenta converter a string em um número.
            return true; // Se não houver exceção, a conversão foi bem-sucedida.
        } catch (NumberFormatException e) {
            return false; // Se ocorrer uma exceção, a string não é um número.
        }
    }

    public List<Promotion> findAll(){

        return promotionRepository.findAll();
    }

    public Promotion findById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException("Promotion not found for ID: " + id));
    }

    public void delete(Long id){

        promotionRepository.deleteById(id);

    }

}
