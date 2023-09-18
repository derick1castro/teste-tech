package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.dtos.PromotionRecordDto;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.services.ProductService;
import com.backend.Manager_restaurant.services.PromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/promotion/v1")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> savePromotion(@RequestBody PromotionRecordDto promotionRecordDto){
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
        if(promotion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(promotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePromotion(@PathVariable(value = "id") Long id,
                                                   @RequestBody PromotionRecordDto promotionRecordDto) {
        var oldPromotion = promotionService.findById(id);
        if (oldPromotion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
        }
        var newPromotion = oldPromotion;
        BeanUtils.copyProperties(promotionRecordDto, newPromotion);
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.save(newPromotion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePromotion(@PathVariable(value = "id") Long id){
        var promotion = promotionService.findById(id);
        if (promotion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
        }
        promotionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Promotion deleted successfully.");
    }

}
