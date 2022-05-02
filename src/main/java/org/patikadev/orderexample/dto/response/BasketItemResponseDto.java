package org.patikadev.orderexample.dto.response;

import java.math.BigDecimal;

public record BasketItemResponseDto(Long id, ProductResponseDto productResponseDto,
                                    BigDecimal quantity, BigDecimal price,
                                    BigDecimal discountPrice,
                                    BigDecimal taxPrice,
                                    BigDecimal shippingPrice) {
}
