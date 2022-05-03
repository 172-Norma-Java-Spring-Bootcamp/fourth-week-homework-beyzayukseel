package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCategoryDto;
import org.patikadev.orderexample.dto.response.CategoryResponseDto;
import org.patikadev.orderexample.model.Category;
import org.springframework.stereotype.Component;

@Component
public record CategoryDtoConverter() {

    public Category convertToEntity(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setName(createCategoryDto.name());
        category.setParent(createCategoryDto.parent());
        return category;
    }

    public CreateCategoryDto convertToCategoryDto(Category category) {
        return new CreateCategoryDto(category.getParent(), category.getName());
    }

    public CategoryResponseDto convertToCategoryResponseDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName(), category.getParent());
    }

    public Category convertToCategory(CategoryResponseDto categoryResponseDto) {
        Category category = new Category();
        category.setId(categoryResponseDto.id());
        category.setName(categoryResponseDto.name());
        category.setParent(categoryResponseDto.parent());
        return category;
    }
}
