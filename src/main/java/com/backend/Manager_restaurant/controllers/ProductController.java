package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.dtos.RestaurantRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var product = new Product();
        BeanUtils.copyProperties(productRecordDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id){
        var product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid ProductRecordDto productRecordDto) {
        var oldProduct = productService.findById(id);
        var newProduct = oldProduct;
        BeanUtils.copyProperties(productRecordDto, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
        var product = productService.findById(id);

        try {
            if (product == null) {
                throw new CategoryNotFoundException("Product not found for ID: " + id);
            }
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
