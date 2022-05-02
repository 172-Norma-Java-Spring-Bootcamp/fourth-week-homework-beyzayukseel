package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.CategoryDtoConverter;
import org.patikadev.orderexample.dto.request.CreateCategoryDto;
import org.patikadev.orderexample.dto.response.CategoryResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.Category;
import org.patikadev.orderexample.repository.CategoryRepository;
import org.patikadev.orderexample.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDtoConverter categoryDtoConverter;
    private final CategoryRepository categoryRepository;


    @Override
    public void create(CreateCategoryDto createCategoryDto) {
        Category category = categoryDtoConverter.convertToEntity(createCategoryDto);
        categoryRepository.save(category);
        log.info("Category ID saved  -> {} date:{}", category.getId(), new Date());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.CategoryNotFoundException("Category not found"));
        return categoryDtoConverter.convertToCategoryResponseDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.CategoryNotFoundException("Category not found"));
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoConverter::convertToCategoryResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto findCategoryByName(String name) {
        return categoryDtoConverter.convertToCategoryResponseDto(categoryRepository.findCategoryByName(name));
    }

    @Override
    public Boolean checkCategory(String name) {
        return categoryRepository.existsByName(name);
    }
}
