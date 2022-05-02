package org.patikadev.orderexample.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.ProductDtoConverter;
import org.patikadev.orderexample.dto.request.CreateProductDto;
import org.patikadev.orderexample.dto.response.ProductResponseDto;
import org.patikadev.orderexample.exception.ServiceOperationException;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.repository.ProductRepository;
import org.patikadev.orderexample.service.CategoryService;
import org.patikadev.orderexample.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDtoConverter productDtoConverter;
    private final ProductRepository productRepository;
    private final BrandServiceImpl brandService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public void create(CreateProductDto createProductDto) {

        if (brandService.checkBrand(createProductDto.brand().getName())) {
            Long id = brandService.getBrandByName(createProductDto.brand().getName()).id();
            createProductDto.brand().setId(id);
            System.out.println(id);
        } else {
            throw new ServiceOperationException.BrandNotFoundException("Brand not found!");
        }
        productRepository.save((productDtoConverter.convertToEntity(createProductDto)));
        log.info("Product date: {} created", new Date());
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.ProductNotFoundException("Product not found"));
        log.info("Product ID -> {} date:{} Get Method ", product.getId(), new Date());
        return productDtoConverter.convertToDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Product ID -> {} date:{} deleted ", id, new Date());
    }

    @Override
    public List<ProductResponseDto> findProductsByCategory(Long categoryId) {
        return productRepository.findProductsByCategory(categoryId)
                .stream()
                .map(productDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
