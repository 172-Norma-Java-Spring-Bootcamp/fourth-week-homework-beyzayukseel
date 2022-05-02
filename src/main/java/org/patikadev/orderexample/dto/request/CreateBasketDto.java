package org.patikadev.orderexample.dto.request;

import org.patikadev.orderexample.dto.response.CustomerResponseDto;

import java.math.BigDecimal;

public record CreateBasketDto(CustomerResponseDto customerResponseDto,
                              BigDecimal discountPrice,
                              BigDecimal taxPrice,
                              BigDecimal shippingPrice,
                              BigDecimal totalPrice) {
}
