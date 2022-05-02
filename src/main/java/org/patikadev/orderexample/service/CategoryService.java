package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCategoryDto;
import org.patikadev.orderexample.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    void create(CreateCategoryDto createCategoryDto);

    CategoryResponseDto getCategoryById(Long id);

    void deleteCategory(Long id);

    List<CategoryResponseDto> getAllCategories();

    Boolean checkCategory(String name);

    CategoryResponseDto findCategoryByName(String name);
}
