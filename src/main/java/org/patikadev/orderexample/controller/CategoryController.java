package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCategoryDto;
import org.patikadev.orderexample.dto.response.CategoryResponseDto;
import org.patikadev.orderexample.service.CategoryService;
import org.patikadev.orderexample.validator.implementation.CategoryIDValidator;
import org.patikadev.orderexample.validator.implementation.CreateCategoryValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryIDValidator categoryIdValidator;
    private final CreateCategoryValidator createCategoryDtoValidator;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        createCategoryDtoValidator.validate(createCategoryDto);
        categoryService.create(createCategoryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long id) {
        categoryIdValidator.validate(id);
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryIdValidator.validate(id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
