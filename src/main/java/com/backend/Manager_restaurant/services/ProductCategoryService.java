package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.repositories.ProductCategoryRepository;
import com.backend.Manager_restaurant.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategory save(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }

    public ProductCategory findById(Long id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        return productCategory.get();
    }
    public void delete(Long id){
        try {
            productCategoryRepository.deleteById(id);
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
