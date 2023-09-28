package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.dtos.PromotionRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.exceptions.PromotionNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.Promotion;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.services.ProductService;
import com.backend.Manager_restaurant.services.PromotionService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/promotion")
@Tag(name = "Promotion", description = "Endpoint for Managing Promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    @Operation(summary = "Adds a new Promotion",
            description = "Adds a new Promotion",
            tags = {"Promotion"},
            method = "POST",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = Promotion.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Promotion> savePromotion(@RequestBody @Valid PromotionRecordDto promotionRecordDto){
        var promotion = new Promotion();
        BeanUtils.copyProperties(promotionRecordDto, promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.save(promotion));
    }

    @GetMapping
    @Operation(summary = "Finds all Promotions",
            description = "Finds all Promotions",
            tags = {"Promotion"},
            method = "GET",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Promotion.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Page<Promotion>> getAllPromotions(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Promotion",
            description = "Finds a Promotion",
            tags = {"Promotion"},
            method = "GET",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = Promotion.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> getOnePromotion(@PathVariable(value = "id") Long id){
        var promotion = promotionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(promotion);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Promotion",
            description = "Updates a Promotion",
            tags = {"Promotion"},
            method = "PUT",
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = Promotion.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Object> updatePromotion(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid PromotionRecordDto promotionRecordDto) {
        var oldPromotion = promotionService.findById(id);
        var newPromotion = oldPromotion;
        BeanUtils.copyProperties(promotionRecordDto, newPromotion);
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.save(newPromotion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Promotion",
            description = "Deletes a Promotion",
            tags = {"Promotion"},
            method = "DELETE",
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
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
