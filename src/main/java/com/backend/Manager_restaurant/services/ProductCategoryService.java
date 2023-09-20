package com.backend.Manager_restaurant.services;

import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.exceptions.WrongValueException;
import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategory save(ProductCategory productCategory){

        if (productCategory.getName() == null || productCategory.getName().trim().isEmpty() || isNumeric(productCategory.getName())) {
            throw new WrongValueException("Please set a valid value");
        }
            return productCategoryRepository.save(productCategory);
    }

    private boolean isNumeric(String str) {

        try {
            Double.parseDouble(str); // Tenta converter a string em um número.
            return true; // Se não houver exceção, a conversão foi bem-sucedida.
        } catch (NumberFormatException e) {
            return false; // Se ocorrer uma exceção, a string não é um número.
        }
    }

    public List<ProductCategory> findAll(){

        return productCategoryRepository.findAll();
    }

    public ProductCategory findById(Long id) {

        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category not found for ID: " + id);
        }
        return productCategory.get();
    }
    public void delete(Long id){
        productCategoryRepository.deleteById(id);

    }
}
