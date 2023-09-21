package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductCategoryRecordDto;
import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.services.ProductCategoryService;
import com.backend.Manager_restaurant.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> saveProduct(@RequestBody @Valid ProductCategoryRecordDto productCategoryRecordDTO){
        var productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryRecordDTO, productCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.save(productCategory));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProductCategory(@PathVariable(value = "id") Long id){
        var productCategory = productCategoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductCategory(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ProductCategoryRecordDto productRecordDto) {
        var oldProduct = productCategoryService.findById(id);
        var newProduct = oldProduct;
        BeanUtils.copyProperties(productRecordDto, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductCategory(@PathVariable(value = "id") Long id){
        var productCategory = productCategoryService.findById(id);

        try {
            if (productCategory == null) {
                throw new CategoryNotFoundException("Category not found for ID: " + id);
            }
            productCategoryService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
