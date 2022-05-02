package org.patikadev.orderexample.dto.request;

import org.patikadev.orderexample.model.Brand;

import java.math.BigDecimal;
import java.util.UUID;


public record CreateProductDto(String name, BigDecimal price, UUID barcode,
                               String image,
                               Brand brand,
                               CreateCategoryDto createCategoryDto) {
}
