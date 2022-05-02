package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateProductDto;
import org.patikadev.orderexample.dto.response.ProductResponseDto;
import org.patikadev.orderexample.model.Product;
import org.springframework.stereotype.Component;

@Component
public record ProductDtoConverter(BrandDtoConverter brandDtoConverter, CategoryDtoConverter categoryDtoConverter) {

    public Product convertToEntity(CreateProductDto createProductDTO) {
        return Product.builder()
                .name(createProductDTO.name())
                .price(createProductDTO.price())
                .image(createProductDTO.image())
                .barcode(createProductDTO.barcode())
                .category(categoryDtoConverter.convertToEntity(createProductDTO.createCategoryDto()))
                .brand(createProductDTO.brand())
                .build();
    }

    public CreateProductDto convert(Product product) {
        return new CreateProductDto(
                product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand(),
                categoryDtoConverter.convertToCategoryDto(product.getCategory()));
    }

    public ProductResponseDto convertToDto(Product product) {
        return new ProductResponseDto(
                product.getId(), product.getName(), product.getPrice(),
                product.getBarcode(), product.getImage(), product.getBrand(),
                categoryDtoConverter.convertToCategoryResponseDto(product.getCategory()));
    }

    public Product convertToProductResponseEntity(ProductResponseDto productResponseDto) {
        Product product = new Product(
                productResponseDto.name(),
                productResponseDto.price(),
                productResponseDto.barcode(),
                productResponseDto.image(),
                productResponseDto.brand(),
                categoryDtoConverter.convertToCategory(productResponseDto.categoryResponseDto()));
        return product;
    }
}
