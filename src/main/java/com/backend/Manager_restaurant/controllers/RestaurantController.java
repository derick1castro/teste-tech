package com.backend.Manager_restaurant.controllers;
import com.backend.Manager_restaurant.dtos.RestaurantRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Restaurant", description = "Endpoint for Managing Restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "Adds a new Restaurant",
            description = "Adds a new Restaurant",
            tags = {"Restaurant"},
            method = "POST",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = Restaurant.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody @Valid RestaurantRecordDto restaurantRecordDto) {
        var restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantRecordDto, restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }


    @GetMapping
    @Operation(summary = "Finds all Restaurants",
            description = "Finds all Restaurants",
            tags = {"Restaurant"},
            method = "GET",
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Restaurant.class))
                            )
                        }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
        )
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Restaurant",
            description = "Finds a Restaurant",
            tags = {"Restaurant"},
            method = "GET",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                                    @Content(schema = @Schema(implementation = Restaurant.class))
                            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> getOneRestaurant(@PathVariable(value = "id") Long id){
        var restaurant = restaurantService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Restaurant",
            description = "Updates a Restaurant",
            tags = {"Restaurant"},
            method = "PUT",
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = Restaurant.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> updateRestaurant(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid RestaurantRecordDto restaurantRecordDto) {
        var oldRestaurant = restaurantService.findById(id);
        var newRestaurant = oldRestaurant;
        BeanUtils.copyProperties(restaurantRecordDto, newRestaurant);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.save(newRestaurant));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Restaurant",
            description = "Deletes a Restaurant",
            tags = {"Restaurant"},
            method = "PUT",
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
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
