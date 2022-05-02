package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateProductDto;
import org.patikadev.orderexample.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    void create(CreateProductDto createProductDto);

    ProductResponseDto getProduct(Long id);

    void deleteProduct(Long id);

    List<ProductResponseDto> findProductsByCategory(Long categoryId);

    List<ProductResponseDto> getAllProducts();

}
