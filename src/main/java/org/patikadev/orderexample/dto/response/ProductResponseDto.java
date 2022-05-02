package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.Brand;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(Long id, String name, BigDecimal price, UUID barcode,
                                 String image,
                                 Brand brand,
                                 CategoryResponseDto categoryResponseDto) {
}
