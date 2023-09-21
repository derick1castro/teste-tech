package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.dtos.PromotionRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.exceptions.PromotionNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.services.ProductService;
import com.backend.Manager_restaurant.services.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> savePromotion(@RequestBody @Valid PromotionRecordDto promotionRecordDto){
        var promotion = new Promotion();
        BeanUtils.copyProperties(promotionRecordDto, promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.save(promotion));
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions(){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePromotion(@PathVariable(value = "id") Long id){
        var promotion = promotionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(promotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePromotion(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid PromotionRecordDto promotionRecordDto) {
        var oldPromotion = promotionService.findById(id);
        var newPromotion = oldPromotion;
        BeanUtils.copyProperties(promotionRecordDto, newPromotion);
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.save(newPromotion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePromotion(@PathVariable(value = "id") Long id){
        var promotion = promotionService.findById(id);
        try {
            if (promotion == null) {
                throw new PromotionNotFoundException("Promotion not found for ID: " + id);
            }
            promotionService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Promotion deleted successfully.");

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
