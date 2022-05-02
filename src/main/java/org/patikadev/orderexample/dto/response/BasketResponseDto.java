package org.patikadev.orderexample.dto.response;

import java.math.BigDecimal;
import java.util.List;


public record BasketResponseDto(List<BasketItemResponseDto> items,
                                BigDecimal price,
                                BigDecimal discountPrice,
                                BigDecimal taxPrice,
                                BigDecimal shippingPrice,
                                BigDecimal totalPrice) {
}
