package com.backend.Manager_restaurant.services;

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

//    public Restaurant save(Restaurant restaurant) {
//
//        Restaurant restaurantSaved = restaurantRepository.save(restaurant);
//
//        List<Product> products = restaurant.getProducts();
//
//        for (Product product : products) {
//            product.getRestaurant().add(restaurantSaved);
//        }
//
//        productRepository.saveAll(products);
//
//        return restaurantSaved;
//    }
public Restaurant save(Restaurant restaurant) {
//    // Salvar o restaurante no repositório de restaurantes.
//    restaurantRepository.save(restaurant);
//
//    // Associar o restaurante aos produtos existentes.
//    List<Product> products = productRepository.findAll();
//
//    for (Product product : products) {
//        if (!product.getRestaurant().contains(restaurant)) {
//            product.getRestaurant().add(restaurant);
//            productRepository.save(product);
//        }
//    }
//    return restaurant;
    // Salvar o restaurante no repositório de restaurantes.
    restaurantRepository.save(restaurant);

    // Associar o restaurante apenas aos produtos da requisição.
    List<Product> productsInRequest = restaurant.getProducts();

    for (Product product : productsInRequest) {
        product.getRestaurant().add(restaurant);
        productRepository.save(product);
    }

    return restaurant;
}

    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id){
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            return restaurant.get();

    }

//    public Restaurant update(Long id, Restaurant oldRestaurant){
//        try {
//            var newRestaurant = restaurantRepository.getReferenceById(id);
//            updateData(newRestaurant, oldRestaurant);
//            return restaurantRepository.save(newRestaurant);
//        } catch (RuntimeException e){
//            e.printStackTrace();
//            throw e;
//        }
//    }

//    private void updateData(Restaurant newRestaurant, Restaurant oldRestaurant){
//        newRestaurant.setAddress(oldRestaurant.getAddress());
//        newRestaurant.setImg(oldRestaurant.getImg());
//        newRestaurant.setHours(oldRestaurant.getHours());
//    }

    public void delete(Long id){
        try {
            restaurantRepository.deleteById(id);
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }


}
