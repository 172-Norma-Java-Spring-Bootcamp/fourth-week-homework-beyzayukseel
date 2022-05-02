package org.patikadev.orderexample.dto.request;

import java.math.BigDecimal;

public record CreateBasketItemDto(Long productId,
                                  Long basketId,
                                  BigDecimal quantity,
                                  BigDecimal price,
                                  BigDecimal discountPrice,
                                  BigDecimal taxPrice,
                                  BigDecimal shippingPrice) {
}
