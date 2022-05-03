package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateProductDto;
import org.patikadev.orderexample.dto.response.ProductResponseDto;
import org.patikadev.orderexample.service.ProductService;
import org.patikadev.orderexample.validator.implementation.CreateProductValidator;
import org.patikadev.orderexample.validator.implementation.ProductIDValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductIDValidator productIdValidator;
    private final CreateProductValidator createProductValidator;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto createProductDto) {
        createProductValidator.validate(createProductDto);
        productService.create(createProductDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        productIdValidator.validate(id);
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> findProductsByCategoryId(@PathVariable Long id) {
        productIdValidator.validate(id);
        return ResponseEntity.ok(productService.findProductsByCategory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productIdValidator.validate(id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
