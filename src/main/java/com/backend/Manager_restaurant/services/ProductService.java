package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.exceptions.WrongValueException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.repositories.ProductRepository;
import com.backend.Manager_restaurant.repositories.PromotionRepository;
import com.backend.Manager_restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (isNumeric(product.getName())) {
            throw new WrongValueException("Please set a valid value");
        }
            return productRepository.save(product);
    }

    private boolean isNumeric(String str) {

        try {
            Double.parseDouble(str); // Tenta converter a string em um número.
            return true; // Se não houver exceção, a conversão foi bem-sucedida.
        } catch (NumberFormatException e) {
            return false; // Se ocorrer uma exceção, a string não é um número.
        }
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new CategoryNotFoundException("Product not found for ID: " + id);
        }
        return product.get();
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
