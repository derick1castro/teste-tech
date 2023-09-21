package com.backend.Manager_restaurant.controllers;
import com.backend.Manager_restaurant.dtos.RestaurantRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody @Valid RestaurantRecordDto restaurantRecordDto) {
        var restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantRecordDto, restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRestaurant(@PathVariable(value = "id") Long id){
        var restaurant = restaurantService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRestaurant(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid RestaurantRecordDto restaurantRecordDto) {
        var oldRestaurant = restaurantService.findById(id);
        var newRestaurant = oldRestaurant;
        BeanUtils.copyProperties(restaurantRecordDto, newRestaurant);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.save(newRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRestaurant(@PathVariable(value = "id") Long id) {
        var restaurant = restaurantService.findById(id);
        try {
            if (restaurant == null) {
                throw new CategoryNotFoundException("Restaurant not found for ID: " + id);
            }
            restaurantService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Restaurant deleted successfully.");

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }


}
