package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductCategoryRecordDto;
import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.services.ProductCategoryService;
import com.backend.Manager_restaurant.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/v1")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> saveProduct(@RequestBody ProductCategoryRecordDto productCategoryRecordDTO){
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
        if(productCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductCategory(@PathVariable(value = "id") Long id,
                                                @RequestBody ProductCategoryRecordDto productRecordDto) {
        var oldProduct = productCategoryService.findById(id);
        if (oldProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found");
        }
        var newProduct = oldProduct;
        BeanUtils.copyProperties(productRecordDto, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductCategory(@PathVariable(value = "id") Long id){
        var productCategory = productCategoryService.findById(id);
        if (productCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found");
        }
        productCategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product Category deleted successfully.");
    }
}
