package org.patikadev.orderexample.dto.request;

import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.model.OrderItemStatus;

import java.math.BigDecimal;
import java.util.Date;

public record CreateOrderItemDto(BigDecimal quantity, Order order,
                                 OrderItemStatus orderStatus,
                                 Date shippingDate,
                                 Date deliveredDate) {
}
