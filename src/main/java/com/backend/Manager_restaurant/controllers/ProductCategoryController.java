package com.backend.Manager_restaurant.controllers;

import com.backend.Manager_restaurant.dtos.ProductCategoryRecordDto;
import com.backend.Manager_restaurant.dtos.ProductRecordDto;
import com.backend.Manager_restaurant.exceptions.CategoryNotFoundException;
import com.backend.Manager_restaurant.models.Product;
import com.backend.Manager_restaurant.models.ProductCategory;
import com.backend.Manager_restaurant.models.Restaurant;
import com.backend.Manager_restaurant.services.ProductCategoryService;
import com.backend.Manager_restaurant.services.ProductService;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/category")
@Tag(name = "Category", description = "Endpoint for Managing Categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    @Operation(summary = "Adds a new Category",
            description = "Adds a new Category",
            tags = {"Category"},
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
    public ResponseEntity<ProductCategory> saveProduct(@RequestBody @Valid ProductCategoryRecordDto productCategoryRecordDTO){
        var productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryRecordDTO, productCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.save(productCategory));
    }

    @GetMapping
    @Operation(summary = "Finds all Categories",
            description = "Finds all Categories",
            tags = {"Category"},
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
    public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Category",
            description = "Finds a Category",
            tags = {"Category"},
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
    public ResponseEntity<Object> getOneProductCategory(@PathVariable(value = "id") Long id){
        var productCategory = productCategoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Category",
            description = "Updates a Category",
            tags = {"Category"},
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
    public ResponseEntity<Object> updateProductCategory(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ProductCategoryRecordDto productRecordDto) {
        var oldProduct = productCategoryService.findById(id);
        var newProduct = oldProduct;
        BeanUtils.copyProperties(productRecordDto, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(newProduct));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Category",
            description = "Deletes a Category",
            tags = {"Category"},
            method = "DELETE",
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
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
