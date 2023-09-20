package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.exceptions.RestaurantNotFoundException;
import com.backend.Manager_restaurant.exceptions.WrongValueException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.repositories.ProductRepository;
import com.backend.Manager_restaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

public Restaurant save(Restaurant restaurant) {

    if (isNumeric(restaurant.getName()) || isNumeric(restaurant.getAddress())) {
        throw new WrongValueException("Please set a valid value");
    }

    restaurantRepository.save(restaurant);

    // Associar o restaurante apenas aos produtos da requisição.
    List<Product> productsInRequest = restaurant.getProducts();

    for (Product product : productsInRequest) {
        product.getRestaurant().add(restaurant);
        productRepository.save(product);
    }

    return restaurant;
}

    private boolean isNumeric(String str) {

        try {
            Double.parseDouble(str); // Tenta converter a string em um número.
            return true; // Se não houver exceção, a conversão foi bem-sucedida.
        } catch (NumberFormatException e) {
            return false; // Se ocorrer uma exceção, a string não é um número.
        }
    }

    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id){

            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if (restaurant.isEmpty()) {
                throw new RestaurantNotFoundException("Restaurant not found for ID: " + id);
            }
            return restaurant.get();

    }

    public void delete(Long id){
        restaurantRepository.deleteById(id);

    }


}
