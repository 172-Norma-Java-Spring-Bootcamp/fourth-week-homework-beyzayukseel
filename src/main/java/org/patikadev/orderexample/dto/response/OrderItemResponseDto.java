package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.model.OrderItemStatus;

import java.math.BigDecimal;
import java.util.Date;

public record OrderItemResponseDto(BigDecimal quantity, Order order,
                                   OrderItemStatus orderStatus,
                                   Date shippingDate,
                                   Date deliveredDate) {
}
