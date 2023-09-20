package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.repositories.ProductRepository;
import com.backend.Manager_restaurant.repositories.PromotionRepository;
import com.backend.Manager_restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public Product save(Product product){
        try {
            return productRepository.save(product);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }


    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }
    public void delete(Long id){
        try {
            productRepository.deleteById(id);
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

}
